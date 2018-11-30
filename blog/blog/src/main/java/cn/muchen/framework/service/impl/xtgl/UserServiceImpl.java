package cn.muchen.framework.service.impl.xtgl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.framework.Constants;
import cn.muchen.framework.dao.mapper.xtgl.User2RoleMapper;
import cn.muchen.framework.dao.mapper.xtgl.UserMapper;
import cn.muchen.framework.service.inter.xtgl.RightService;
import cn.muchen.framework.service.inter.xtgl.UserService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：UserServiceImpl 
 * @Description： 用户数据处理类实现
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:44:58 
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/** 
	 * @Description sys_user表数据库操作对象
	 */
	@Autowired
	UserMapper userMapper;
	
	/**
	 * @Description sys_user2role表数据库操作对象
	 */
	@Autowired
	User2RoleMapper user2RoleMapper;
	
	/** 
	 * @Description 菜单权限数据库操作对象
	 */
	@Autowired
	RightService rightServiceImpl;
	
	/** 
	 * @Description 日志打印对象 
	 */ 
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	/**
	 * <p>Title：findUserByLoginid</p> 
	 * <p>Description：  根据登录id查询用户</p> 
	 * @param loginid 登录id
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#findUserByLoginid(java.lang.String)
	 */
	@Override
	public Map<String, Object> findUserByLoginid(String loginid) {
		logger.info("【user operator】查询用户，条件为loginid=" + loginid);
		/** 根据loginID查询用户 */
		Map<String, Object> user = userMapper.getUserByLoginid(loginid);
		
		/** 存在用户则查询权限 */
		if (!Util.isEmpty(user)) {
			user.put("RIGHTIS", rightServiceImpl.getUserRihts("" + user.get("USERID")));
		}
		logger.info("【user query】登录用户信息：" + user);
 		return user;
	}


	/**
	 * 
	 * <p>Title：getUsersByPage</p> 
	 * <p>Description： 分页查询用户信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#getUsersByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getUsersByPage(Map<String, Object> params) {
		logger.info("【UserServiceImpl】分页查询用户信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", userMapper.getUserByPage(params));
		rtnMap.put("count", userMapper.getUserCountByPage(params));
		return rtnMap;
	}


	/**
	 * 
	 * <p>Title：saveUser</p> 
	 * <p>Description：保存用户 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#saveUser(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveUser(Map<String, Object> params) {
		logger.info("【UserServiceImpl】保存用户信息");
		Map<String, Object> rtnMap = new HashMap<>();
		try {
			// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
			if (Util.isEmpty(params.get("USERID"))) {
				params.put("BAE002", params.get("OPERATORID"));
				params.put("BAE004", params.get("OPERATORID"));
				
				userMapper.insert(params);
			} else {
				params.put("BAE004", params.get("OPERATORID"));
				userMapper.update(params);
			}
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【UserServiceImpl.saveUser】保存用户信息失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "保存失败");
		}
		return rtnMap;
	}


	/**
	 * 
	 * <p>Title：deleteUser</p> 
	 * <p>Description：删除用户信息 </p> 
	 * @param userId
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#deleteUser(java.lang.Integer)
	 */
	@Override
	public boolean deleteUser(Integer userId) {
		logger.info("【UserServiceImpl】删除用户:" + userId);
		
		// 调用DAO删除菜单
		if (userMapper.deleteById(userId) == 1) {
			return true;
		}
		
		return false;
	}


	/**
	 * 
	 * <p>Title：getAuthorizeByPage</p> 
	 * <p>Description：分页查询用户已有角色信息 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#getAuthorizeByPage(java.util.Map)
	 */
	@Override
	public Map<String, Object> getAuthorizeByPage(Map<String, Object> params) {
		logger.info("【UserServiceImpl.getAuthorizeByPage】分页查询用户已有角色信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", user2RoleMapper.getUserRoleByPage(params));
		rtnMap.put("count", user2RoleMapper.getUserRoleCount(params));
		return rtnMap;
	}

	
	/**
	 * 
	 * <p>Title：getUserUnAuthorizeByPag</p> 
	 * <p>Description：查询用户没有的权限信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#getUserUnAuthorizeByPag(java.util.Map)
	 */
	@Override
	public Map<String, Object> getUserUnAuthorizeByPag(Map<String, Object> params) {
		logger.info("【UserServiceImpl.getUserUnAuthorizeByPag】分页查询用户没有有角色信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", user2RoleMapper.getUserUnRoleByPage(params));
		rtnMap.put("count", user2RoleMapper.getUserUnRoleCount(params));
		return rtnMap;
	}


	@Override
	public Map<String, Object> authorizeUserRole(Map<String, Object> params) {
		logger.info("【UserServiceImpl.authorizeUserRole】用户授权");
		Map<String, Object> rtnMap = new HashMap<>();
		// 菜单串
		String[] roleIds = ((String) params.get("ROLEIDS")).split(",");
		
		Map<String, Object> teMap = new HashMap<>();
		
		// 新增角色权限信息
		try {
			teMap.put("USERID", params.get("USERID"));  // 角色id
			teMap.put("VALIDATED", "1");  // 有效标志
			teMap.put("MEMO", "前台新增授权");  // 备注
			teMap.put("BAE002", params.get("OPERATOR"));  // 创建人
			teMap.put("BAE004", params.get("OPERATOR"));  // 最近修改人
			// 遍历菜单id进行保存
			for (String roleId : roleIds) {
				if (!Util.isEmpty(roleId)) {
					teMap.put("ROLEID", roleId);
					user2RoleMapper.insert(teMap);
				}
			}
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【UserServiceImpl.authorizeUserRole】用户授权失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "授权错误");
		}
		return rtnMap;
	}


	/**
	 * 
	 * <p>Title：unAuthorizeUserRole</p> 
	 * <p>Description： 取消用户授权</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.framework.service.inter.xtgl.UserService#unAuthorizeUserRole(java.util.Map)
	 */
	@Override
	public boolean unAuthorizeUserRole(Map<String, Object> params) {
		logger.info("【UserServiceImpl.unAuthorizeUserRole】取消用户授权");
		
		// 角色权限串
		String[] user2RoleIds = ((String) params.get("USER2ROLEID")).split(",");
		
		try {
			// 遍历角色权限串进行删除
			for (String user2RoleId : user2RoleIds) {
				// 删除角色权限信息
				if (user2RoleMapper.delete(Integer.parseInt(user2RoleId)) != 1) {
					throw new Exception("取消用户授权失败");
				}
			}
		} catch (Exception e) {
			logger.error("【UserServiceImpl.unAuthorizeUserRole】取消用户授权失败:" + e);
			return false;
		}
		
		return true;
	}
}
