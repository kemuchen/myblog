package cn.muchen.blog.server.dao.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: ArticeTageMapper 
 * @Description: 文章标签管理
 * @author: 柯雷
 * @date：2018年11月24日 下午4:28:54
 */
@Repository
public interface ArticleTagMapper {
	/**
	 * @Title：insertArticeTag
	 * @Description：新增文章标签
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer insertArticleTag(Map<String, Object> params);
	
	/**
	 * @Title：deleteArticeTag
	 * @Description：删除文章标签
	 * @param ：@param criticid
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer deleteArticleTag(Integer criticid);
	
	/**
	 * @Title：pageQueryArticeTags
	 * @Description: 查询文章标签
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	List<Map<String, Object>> pageQueryArticeTags(Map<String, Object> params);
	
	/**
	 * @Title：articelTypeCount 
	 * @Description：查询文章标签数量
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	Integer articelTagCount(Map<String, Object> params);
	
	/**
	 * @Title：getArticleType 
	 * @Description：查询文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	Map<String, Object> getArticleTag(Map<String, Object> params);
	
	/**
	 * @Title：updateArticeTag
	 * @Description：更新文章标签
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Integer 
	 * @throws
	 */
	public Integer updateArticleTag(Map<String, Object> params);
}
