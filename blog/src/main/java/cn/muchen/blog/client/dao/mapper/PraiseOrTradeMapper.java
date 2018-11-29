package cn.muchen.blog.client.dao.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：PraiseOrTradeMapper 
 * @Description： 评论或踩DAO
 * @author ：柯雷
 * @date ：2018年11月15日 下午5:08:09 
 *
 */
@Repository
public interface PraiseOrTradeMapper {
	
	/**
	 * @Title：insert 
	 * @Description：新增点赞或踩信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insert(Map<String, Object> params);
	
	/**
	 * @Title：getCount 
	 * @Description：获取点赞或踩数量
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getCount(Map<String, Object> params);
}
