package cn.muchen.framework.dao.mapper.xtgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: RoleMapper 
 * @Description: 角色DAO处理接口
 * @author: 柯雷
 * @date：2018年9月9日 下午10:11:23
 */
@Repository
public interface RoleMapper {
	
	/**
	 * 
	 * @Title：insert 
	 * @Description：新增角色
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insert(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：delete 
	 * @Description：删除角色
	 * @param ：@param roleId
	 * @return ：Integer 
	 * @throws
	 */
	public Integer delete(Integer roleId);
	
	/**
	 * 
	 * @Title：update 
	 * @Description：更新角色
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer update(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getRoleById 
	 * @Description：根据id查询角色信息
	 * @param ：@param roleId
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getRoleById(Integer roleId);
	
	/**
	 * 
	 * @Title：getRoleByPage 
	 * @Description：分页查询角色
	 * @param ：@param parmas
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRoleByPage(Map<String, Object> parmas);
	
	/**
	 * 
	 * @Title：getRoleCount 
	 * @Description：查询角色数量
	 * @param ：@param parmas
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getRoleCount(Map<String, Object> parmas);
}
