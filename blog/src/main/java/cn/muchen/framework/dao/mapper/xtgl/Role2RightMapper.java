package cn.muchen.framework.dao.mapper.xtgl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: Role2Right 
 * @Description: 角色权限DAO操作接口
 * @author: 柯雷
 * @date：2018年9月9日 下午10:56:39
 */
@Repository
public interface Role2RightMapper {

	/**
	 * 
	 * @Title：insertRole2Right 
	 * @Description：新增角色权限信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insertRole2Right(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteRole2Right 
	 * @Description：取消授权
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer deleteRole2Right(Integer role2RightId);
	
	/**
	 * 
	 * @Title：getRole2RightByPage 
	 * @Description：分页查询角色已有权限信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRole2RightByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getRole2RightCount 
	 * @Description：查询角色已有权限数量
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getRole2RightCount(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getRole2RightByPage 
	 * @Description：分页查询角色没有有的权限信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRoleNot2RightByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getRole2RightCount 
	 * @Description：查询角色没有有的权限数量
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getRoleNot2RightCount(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：update 
	 * @Description：根据角色权限信息
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer update(Map<String, Object> params);
}
