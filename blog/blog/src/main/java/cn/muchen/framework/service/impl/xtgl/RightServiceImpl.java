package cn.muchen.framework.service.impl.xtgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.framework.Constants;
import cn.muchen.framework.dao.mapper.xtgl.RightMapper;
import cn.muchen.framework.service.inter.xtgl.RightService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: RightServiceImpl 
 * @Description: 菜单处理接口实现类
 * @author: 柯雷
 * @date：2018年9月5日 下午9:25:54
 */
@Service
public class RightServiceImpl implements RightService {

	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(RightServiceImpl.class);
	
	/**
	 * @Description 菜单DAO操作对象
	 */
	@Autowired
	RightMapper rightMapper;

	/**
	 * 
	 * <p>Title：getUserRihts</p> 
	 * <p>Description： 根据用户ID查询用户拥有的菜单权限</p> 
	 * @param userid
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RightService#getUserRihts(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getUserRihts(String userid) {
		logger.info("【get rights】获取用户菜单信息，userid=" + userid);
		Map<String, Object> temp = new HashMap<>();
		temp.put("USERID", userid);
		temp.put("RIGHTLEVEL", "1");
		// 获取一级菜单
		List<Map<String, Object>> rightList = rightMapper.getRightsByUserid(temp);
		
		for(Map<String, Object> right : rightList) {
			// 如果是菜单权限则查询子菜单，否则不查询子菜单
			temp.put("RIGHTLEVEL", "2");
			temp.put("RIGHTID", right.get("RIGHTID"));
			right.put("CHILDREN", rightMapper.getRightsByUserid(temp));
		}
		return rightList;
	}

	/**
	 * 
	 * <p>Title：getRightsByPage</p> 
	 * <p>Description： 分页查询菜单信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RightService#getRightsByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getRightsByPage(Map<String, Object> params) {
		logger.info("【RightServiceImpl】分页查询菜单信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", rightMapper.getRightsByPage(params));
		rtnMap.put("count", rightMapper.getRightCount(params));
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：saveRight</p> 
	 * <p>Description：保存菜单信息 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RightService#saveRight(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveRight(Map<String, Object> params) {
		logger.info("【RightServiceImpl】保存菜单信息");
		Map<String, Object> rtnMap = new HashMap<>();
		// 先校验父菜单是否存在
		if ("2".equals(params.get("RIGHTILEVEL")) && Util.isEmpty(rightMapper.getRightById((String) params.get("RIGHTILEVEL")))) {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "父菜单不存在，请核查!");
		} else {
			try {
				// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
				if (Util.isEmpty(rightMapper.getRightById((String) params.get("RIGHTID")))) {
					params.put("BAE002", params.get("USERID"));
					params.put("BAE004", params.get("USERID"));
					
					rightMapper.insert(params);
				} else {
					params.put("BAE004", params.get("USERID"));
					
					rightMapper.update(params);
				}
				
				rtnMap.put("code", Constants.AJAX_FHZ_CG);
				rtnMap.put("message", "");
			} catch (Exception e) {
				logger.error("【RightServiceImpl.saveRight】保存菜单失败：" + e);
				rtnMap.put("code", Constants.AJAX_FHZ_SB);
				rtnMap.put("message", "保存失败");
			}
			
		}
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：deleteRight</p> 
	 * <p>Description： 删除菜单</p> 
	 * @param rightid
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RightService#deleteRight(java.lang.String)
	 */
	@Override
	public boolean deleteRight(String rightid) {
		logger.info("【RightServiceImpl】删除菜单:" + rightid);
		
		// 调用DAO删除菜单
		if (rightMapper.deleteById(rightid) == 1) {
			return true;
		}
		
		return false;
	}

	/**
	 * <p>Title：getAllRight</p> 
	 * <p>Description： 获取所有权限</p> 
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RightService#getAllRight()
	 */
	@Override
	public List<Map<String, Object>> getAllRight() {
		logger.info("【RightServiceImpl.getAllRight】获取所有权限");
		return rightMapper.getAllRight();
	}
}
