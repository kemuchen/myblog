package cn.muchen.blog.server.service.inter;

import java.util.Map;

/**
 * 
 * @ClassName: ArticleTagService 
 * @Description: 文章标签处理接口
 * @author: 柯雷
 * @date：2018年11月24日 下午6:08:15
 */
public interface ArticleTagService {

	/**
	 * @Title：getArticleTags 
	 * @Description：分页查询文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getArticleTags(Map<String, Object> params);
	
	/**
	 * @Title：deleteArticleTag
	 * @Description：删除文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> deleteArticleTag(Map<String, Object> params);
	
	/**
	 * @Title：saveArticleTag 
	 * @Description：保存文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> saveArticleTag(Map<String, Object> params);
}
