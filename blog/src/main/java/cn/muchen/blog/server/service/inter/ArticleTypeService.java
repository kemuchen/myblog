package cn.muchen.blog.server.service.inter;

import java.util.Map;

/**
 * 
 * @ClassName: ArticleTypeService 
 * @Description: 文章分类信息处理接口
 * @author: 柯雷
 * @date：2018年11月24日 下午5:17:18
 */
public interface ArticleTypeService {

	/**
	 * @Title：getArticleTypes 
	 * @Description：分页查询文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> getArticleTypes(Map<String, Object> params);
	
	/**
	 * @Title：deleteArticleType
	 * @Description：删除文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> deleteArticleType(Map<String, Object> params);
	
	/**
	 * @Title：saveArticleType 
	 * @Description：保存文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	public Map<String, Object> saveArticleType(Map<String, Object> params);
}
