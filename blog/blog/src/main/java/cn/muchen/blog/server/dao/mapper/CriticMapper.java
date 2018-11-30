package cn.muchen.blog.server.dao.mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: CriticMapper 
 * @Description: 评论信息DAO操作
 * @author: 柯雷
 * @date：2018年10月1日 上午9:16:19
 */
public interface CriticMapper {
	
	/**
	 * @Title：insertCritic 
	 * @Description：新增评论信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer insertCritic(Map<String, Object> params);
	
	/**
	 * @Title：deleteCritic 
	 * @Description：删除评论信息
	 * @param ：@param criticid
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer deleteCritic(Integer criticid);
	
	/**
	 * @Title：getCritics 
	 * @Description: 查询评论信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	List<Map<String, Object>> getCritics(Map<String, Object> params);
	
	/**
	 * @Title：updateCritic 
	 * @Description：更新评论信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer updateCritic(Map<String, Object> params);
}
