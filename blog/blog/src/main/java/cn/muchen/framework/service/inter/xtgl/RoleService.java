package cn.muchen.framework.service.inter.xtgl;

import java.util.Map;

/**
 * 
 * @ClassName: RoleService 
 * @Description: 角色处理接口
 * @author: 柯雷
 * @date：2018年9月9日 下午10:09:46
 */
public interface RoleService {

	/**
	 *
	 * @Title：getRightsByPage 
	 * @Description：分页查询角色信息
	 * @param ：@param params
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public Map<String, Object> getRightsByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：saveRole 
	 * @Description：保存角色信息
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Map<String, Object> saveRole(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteRole 
	 * @Description: 删除角色信息
	 * @param ：@param roleId
	 * @return ：boolean 
	 * @throws
	 */
	public boolean deleteRole(Integer roleId);
	
	/**
	 * 
	 * @Title：authorizeRole 
	 * @Description：给角色授权
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> authorizeRole(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：authorizeRole 
	 * @Description：取消角色授权
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public boolean unAuthorizeRole(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getAuthorizeByPage 
	 * @Description：查询角色已有权限
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getAuthorizeByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUnAuthorizeByPage 
	 * @Description：查询角色没有的权限信息
	 * @param ：@param params
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getUnAuthorizeByPage(Map<String, Object> params);
}
