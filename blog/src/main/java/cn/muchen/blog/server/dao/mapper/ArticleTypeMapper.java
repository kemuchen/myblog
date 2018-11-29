package cn.muchen.blog.server.dao.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: ArticleTypeMapper 
 * @Description: 文章类别管理
 * @author: 柯雷
 * @date：2018年11月24日 下午4:27:18
 */
@Repository
public interface ArticleTypeMapper {
	/**
	 * @Title：insertArticleType
	 * @Description：新增文章类别
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer insertArticleType(Map<String, Object> params);
	
	/**
	 * @Title：deleteArticleType
	 * @Description：删除文章类别
	 * @param ：@param criticid
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer deleteArticleType(Integer tyid);
	
	/**
	 * @Title：pageQueryArticleTypes
	 * @Description: 查询文章类别
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	List<Map<String, Object>> pageQueryArticleTypes(Map<String, Object> params);
	
	/**
	 * @Title：articelTypeCount 
	 * @Description：查询文章类别数量
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer articleTypeCount(Map<String, Object> params);
	
	/**
	 * @Title：getArticleType 
	 * @Description：查询文章类别信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	Map<String, Object> getArticleType(Map<String, Object> params);
	
	/**
	 * @Title：updateArticleType
	 * @Description：更新文章类别
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer updateArticleType(Map<String, Object> params);
}
