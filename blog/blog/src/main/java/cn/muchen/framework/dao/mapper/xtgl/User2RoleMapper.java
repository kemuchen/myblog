package cn.muchen.framework.dao.mapper.xtgl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：User2RoleMapper 
 * @Description： 用户角色信息DAO
 * @author ：柯雷
 * @date ：2018年9月10日 下午3:15:12 
 *
 */
@Repository
public interface User2RoleMapper {

	/**
	 * 
	 * @Title：insert 
	 * @Description：TODO
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insert(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：delete 
	 * @Description：删除用户角色信息
	 * @param ：@param user2RoleId
	 * @return ：Integer 
	 * @throws
	 */
	public Integer delete(Integer user2RoleId);
	
	/**
	 * 
	 * @Title：update 
	 * @Description：更新用户角色信息
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer update(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserRoleByPage 
	 * @Description：分页获取用户已有角色
	 * @param ：@param params
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getUserRoleByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserRoleCount 
	 * @Description：获取用户已有角色数量
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getUserRoleCount(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserUnRoleByPage 
	 * @Description：获取用户没有的角色
	 * @param ：@param params
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getUserUnRoleByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserUnRoleCount 
	 * @Description：获取用户没有的角色数量
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getUserUnRoleCount(Map<String, Object> params);
}
