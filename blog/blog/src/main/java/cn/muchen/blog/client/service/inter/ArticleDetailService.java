package cn.muchen.blog.client.service.inter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:：ArticleDetailService 
 * @Description： 文章详细内容界面处理接口
 * @author ：柯雷
 * @date ：2018年9月29日 下午3:01:26 
 *
 */
public interface ArticleDetailService {
	
	/**
	 * @Title：getArticleInfo 
	 * @Description：获取文章详细内容界面信息：包括文章信息和评论信息
	 * @param ：@param articleId
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getArticleInfo(String articleId);
	
	/**
	 * @Title：getArticleCritic 
	 * @Description：获取文章评论信息
	 * @param ：@param articleId
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getArticleCritic(Map<String, Object> params);
	
	/**
	 * @Title：praisOrTrade 
	 * @Description：点赞或者踩
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> praisOrTrade(Map<String, Object> params);
	
	/**
	 * @Title：critic 
	 * @Description：评论文章或者回复评论
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> critic(Map<String, Object> params);
	
	/**
	 * @Title：updateRead 
	 * @Description：更新文章浏览量
	 * @param ：@param ARTICLEID
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer updateRead(String ARTICLEID);
	
	/**
	 * @Title：getArticlePage 
	 * @Description：分页查询文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getRankArticles(Map<String, Object> params);
}
