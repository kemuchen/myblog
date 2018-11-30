package cn.muchen.framework.service.inter.xtgl;

import java.util.Map;

/**
 * 
 * @ClassName:：UserService 
 * @Description： 用户数据逻辑处理类接口
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:46:45 
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @Title：findUserByLoginid 
	 * @Description：根据登录id查询用户信息
	 * @param ：@param loginid
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> findUserByLoginid(String loginid);
	
	/**
	 * 
	 * @Title：getAllUsers 
	 * @Description：分页查询用户信息
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getUsersByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：saveUser 
	 * @Description：保存用户
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> saveUser(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteUser 
	 * @Description：删除用户信息
	 * @param ：@param userId
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public boolean deleteUser(Integer userId);
	
	/**
	 * 
	 * @Title：getAuthorizeByPage 
	 * @Description：分页查询用户已有角色信息
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getAuthorizeByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserUnAuthorizeByPag 
	 * @Description：查询用户没有的权限信息
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getUserUnAuthorizeByPag(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：authorizeUserRole 
	 * @Description：用户授权
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> authorizeUserRole(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：unAuthorizeUserRole 
	 * @Description：取消用户授权
	 * @param ：@param params
	 * @return ：boolean 
	 * @throws
	 */
	public boolean unAuthorizeUserRole(Map<String, Object> params);
}
