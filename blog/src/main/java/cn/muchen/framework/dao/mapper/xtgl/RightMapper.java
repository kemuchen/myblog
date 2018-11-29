package cn.muchen.framework.dao.mapper.xtgl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：RightMapper 
 * @Description：菜单处理
 * @author ：柯雷
 * @date ：2018年9月5日 下午5:04:02 
 *
 */
@Repository
public interface RightMapper {
	
	/**
	 * 
	 * @Title：getRightById 
	 * @Description：根据ID查询菜单信息
	 * @param ：@param rightId：菜单id
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getRightById(String rightId);
	
	/**
	 * 
	 * @Title：getAllRitht 
	 * @Description：获取所有菜单信息
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getAllRight();
	
	/**
	 * 
	 * @Title：insert 
	 * @Description：新增菜单信息
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insert(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：update 
	 * @Description：更新菜单
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer update(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteById 
	 * @Description：根据菜单ID删除
	 * @param ：@param rightId
	 * @return ：Integer 
	 * @throws
	 */
	public Integer deleteById(String rightId);
	
	/**
	 * 
	 * @Title：getRightsByLoginid 
	 * @Description：根据登录id查询用户权限
	 * @param ：@param loginid
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRightsByUserid(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getChildren 
	 * @Description：根据菜单ID获取子菜单
	 * @param ：@param rightid
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getChildren(String rightid);
	
	/**
	 * 
	 * @Title：getRightsByPage 
	 * @Description：分页查询菜单信息
	 * @param ：@param params
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRightsByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getRightCount 
	 * @Description：查询菜单总条数
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getRightCount(Map<String, Object> params);
}
