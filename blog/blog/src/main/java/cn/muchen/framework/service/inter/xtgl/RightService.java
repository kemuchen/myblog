package cn.muchen.framework.service.inter.xtgl;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: RightService 
 * @Description: 菜单处理接口
 * @author: 柯雷
 * @date：2018年9月5日 下午9:23:16
 */
public interface RightService {

	/**
	 * 
	 * @Title：getUserRihts 
	 * @Description：根据用户ID查询用户拥有的菜单信息
	 * @param ：@param userid 用户ID
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getUserRihts(String userid); 
	
	/**
	 * 
	 * @Title：getRightsByPage 
	 * @Description：分页查询菜单信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getRightsByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：saveRight 
	 * @Description：TODO
	 * @param ：@param 保存菜单信息
	 * @param ：@return 
	 * @return ：boolean 
	 * @throws
	 */
	public Map<String, Object> saveRight(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteRight 
	 * @Description：删除菜单
	 * @param ：@param rightid
	 * @param ：@return 
	 * @return ：boolean 
	 * @throws
	 */
	public boolean deleteRight(String rightid);
	
	/**
	 * @Title：getAllRight 
	 * @Description：获取所有权限
	 * @param ：@return 
	 * @return ：List<String> 
	 * @throws
	 */
	public List<Map<String, Object>> getAllRight();
}
