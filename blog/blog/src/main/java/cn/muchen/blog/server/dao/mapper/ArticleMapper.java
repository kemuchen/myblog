package cn.muchen.blog.server.dao.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:：ArticelMapper 
 * @Description： 文章信息dao
 * @author ：柯雷
 * @date ：2018年9月28日 上午10:45:01 
 *
 */
@Repository
public interface ArticleMapper {
	
	/**
	 * @Title：getArticleByPage 
	 * @Description：分页查询文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getArticleByPage(Map<String, Object> params);
	
	/**
	 * @Title：getArticleCount 
	 * @Description 获取文章分页总记录数
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer getArticleCount(Map<String, Object> params);
	
	/**
	 * @Title：getArticleById 
	 * @Description：根据文章id查询文章信息
	 * @param ：@param ARTICLEID
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getArticleById(Integer ARTICLEID);
	
	/**
	 * @Title：deleteArticle 
	 * @Description：删除文章信息
	 * @param ：@param ARTICLEID
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer deleteArticle(Integer ARTICLEID);
	
	/**
	 * @Title：updateArticle 
	 * @Description：更新文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer updateArticle(Map<String, Object> params);
	
	/**
	 * @Title：insertArticle 
	 * @Description：新增文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer insertArticle(Map<String, Object> params);
	
	/**
	 * @Title：getRankArticles 
	 * @Description：根据文章点击量获取文章列表
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	public List<Map<String, Object>> getRankArticles(Map<String, Object> params);
}
