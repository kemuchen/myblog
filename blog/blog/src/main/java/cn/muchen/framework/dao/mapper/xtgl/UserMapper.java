package cn.muchen.framework.dao.mapper.xtgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：UserMapper 
 * @Description： 用户处理
 * @author ：柯雷
 * @date ：2018年9月5日 下午5:05:25 
 *
 */
@Repository
public interface UserMapper {
	
	/**
	 * 
	 * @Title：getUserByLoginid 
	 * @Description：根据loginID查询用户信息
	 * @param ：@param loginid：登录id
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getUserByLoginid(String loginid);
	
	/**
	 * 
	 * @Title：getUserById 
	 * @Description： 根据id查询用户信息
	 * @param ：@param id
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getUserById(String id);
	
	/**
	 * 
	 * @Title：getAllUsers 
	 * @Description：获取所有用户
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getAllUsers();
	
	/**
	 * 
	 * @Title：insert 
	 * @Description：新增用户
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insert(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：update 
	 * @Description：更新用户
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer update(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：deleteById 
	 * @Description： 删除用户
	 * @param ：@param id
	 * @return ：Integer 
	 * @throws
	 */
	public Integer deleteById(Integer id);
	
	/**
	 * 
	 * @Title：getUserByPage 
	 * @Description：分页查询用户记录
	 * @param ：@param params
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getUserByPage(Map<String, Object> params);
	
	/**
	 * 
	 * @Title：getUserCountByPage 
	 * @Description：获取分页查询用户记录数
	 * @param ：@param params
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getUserCountByPage(Map<String, Object> params);
}
