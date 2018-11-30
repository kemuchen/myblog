package cn.muchen.framework.service.impl.xtgl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.framework.Constants;
import cn.muchen.framework.dao.mapper.xtgl.Role2RightMapper;
import cn.muchen.framework.dao.mapper.xtgl.RoleMapper;
import cn.muchen.framework.service.inter.xtgl.RoleService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: RoleServiceImpl 
 * @Description: 角色处理实现类
 * @author: 柯雷
 * @date：2018年9月9日 下午10:09:31
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	/**
	 * @Description DAO操作对象
	 */
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	Role2RightMapper role2RightMapper;

	/**
	 * 
	 * <p>Title：getRightsByPage</p> 
	 * <p>Description： 分页查询角色信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#getRightsByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getRightsByPage(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】分页查询角色信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", roleMapper.getRoleByPage(params));
		rtnMap.put("count", roleMapper.getRoleCount(params));
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：saveRole</p> 
	 * <p>Description： 保存角色信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#saveRole(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveRole(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】保存角色信息");
		Map<String, Object> rtnMap = new HashMap<>();
		try {
			// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
			if (Util.isEmpty(params.get("ROLEID"))) {
				params.put("BAE002", params.get("USERID"));
				params.put("BAE004", params.get("USERID"));
				
				roleMapper.insert(params);
			} else {
				params.put("BAE004", params.get("USERID"));
				roleMapper.update(params);
			}
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【RoleServiceImpl.saveRole】保存角色失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "保存失败");
		}
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：deleteRole</p> 
	 * <p>Description：删除角色信息 </p> 
	 * @param roleId
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#deleteRole(java.lang.Integer)
	 */
	@Override
	public boolean deleteRole(Integer roleId) {
		logger.info("【RoleServiceImpl】删除菜单:" + roleId);
		
		// 调用DAO删除菜单
		if (roleMapper.delete(roleId) == 1) {
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * <p>Title：authorizeRole</p> 
	 * <p>Description：角色授权 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#authorizeRole(java.util.Map)
	 */
	@Override
	public Map<String, Object> authorizeRole(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】角色授权");
		Map<String, Object> rtnMap = new HashMap<>();
		// 菜单串
		String[] rightIds = ((String) params.get("RIGHTIDS")).split(",");
		
		Map<String, Object> teMap = new HashMap<>();
		
		// 新增角色权限信息
		try {
			teMap.put("ROLEID", params.get("ROLEID"));  // 角色id
			teMap.put("VALIDATED", "1");  // 有效标志
			teMap.put("MEMO", "前台新增授权");  // 备注
			teMap.put("BAE002", params.get("OPERATOR"));  // 创建人
			teMap.put("BAE004", params.get("OPERATOR"));  // 最近修改人
			// 遍历菜单id进行保存
			for (String rightId : rightIds) {
				if (!Util.isEmpty(rightId)) {
					teMap.put("RIGHTID", rightId);
					role2RightMapper.insertRole2Right(teMap);
				}
			}
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【RoleServiceImpl.authorizeRole】角色授权失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "授权错误");
		}
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：unAuthorizeRole</p> 
	 * <p>Description：取消授权 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#unAuthorizeRole(java.util.Map)
	 */
	@Override
	public boolean unAuthorizeRole(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】取消角色授权");
		
		// 角色权限串
		String[] role2Rightids = ((String) params.get("ROLE2RIGHTID")).split(",");
		
		try {
			// 遍历角色权限串进行删除
			for (String role2rightid : role2Rightids) {
				// 删除角色权限信息
				if (role2RightMapper.deleteRole2Right(Integer.parseInt(role2rightid)) != 1) {
					throw new Exception("取消角色授权失败");
				}
			}
		} catch (Exception e) {
			logger.error("【RoleServiceImpl.unAuthorizeRole】取消角色授权失败:" + e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * <p>Title：getAuthorizeByPage</p> 
	 * <p>Description： </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#getAuthorizeByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getAuthorizeByPage(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】分页查询角色已有的权限信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", role2RightMapper.getRole2RightByPage(params));
		rtnMap.put("count", role2RightMapper.getRole2RightCount(params));
		return rtnMap;
	}

	/**
	 * 
	 * <p>Title：getUnAuthorizeByPage</p> 
	 * <p>Description： 分页查询角色没有的权限信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.RoleService#getUnAuthorizeByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getUnAuthorizeByPage(Map<String, Object> params) {
		logger.info("【RoleServiceImpl】分页查询角色没有的权限信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", role2RightMapper.getRoleNot2RightByPage(params));
		rtnMap.put("count", role2RightMapper.getRoleNot2RightCount(params));
		return rtnMap;
	}
}
