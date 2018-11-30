package cn.muchen.blog.server.service.inter;

import java.util.Map;

/**
 * 
 * @ClassName:：ArticelManagerService 
 * @Description： 文章管理处理接口
 * @author ：柯雷
 * @date ：2018年9月28日 上午10:41:37 
 *
 */
public interface ArticleManagerService {

	/**
	 * @Title：getArticlePage 
	 * @Description：分页查询文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getArticlePage(Map<String, Object> params);
	
	/**
	 * @Title：deleteArticle 
	 * @Description：删除文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> deleteArticle(Map<String, Object> params);
	
	/**
	 * @Title：saveArticle 
	 * @Description：保存文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> saveArticle(Map<String, Object> params);
}
