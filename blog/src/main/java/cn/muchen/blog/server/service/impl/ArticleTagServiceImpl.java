package cn.muchen.blog.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.blog.server.dao.mapper.ArticleTagMapper;
import cn.muchen.blog.server.service.inter.ArticleTagService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: ArticleTagServiceImpl 
 * @Description: 文章标签处理实现
 * @author: 柯雷
 * @date：2018年11月24日 下午6:09:21
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(ArticleTagServiceImpl.class);

	@Autowired
	ArticleTagMapper articleTagMapper;
	
	/**
	 * <p>Title：getArticleTags</p> 
	 * <p>Description：分页查询文章信息 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTagService#getArticleTags(java.util.Map)
	 */
	@Override
	public Map<String, Object> getArticleTags(Map<String, Object> params) {
		logger.info("【ArticleTagServiceImpl】分页查询文章标签信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", articleTagMapper.pageQueryArticeTags(params));
		rtnMap.put("count", articleTagMapper.articelTagCount(params));
		return rtnMap;
	}
	
	/**
	 * <p>Title：deleteArticleTag</p> 
	 * <p>Description：删除文章标签 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTagService#deleteArticleTag(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteArticleTag(Map<String, Object> params) {
		logger.info("【ArticleTagServiceImpl.deleteArticleTag】删除文章标签信息：" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			articleTagMapper.deleteArticleTag(Integer.parseInt((String) params.get("TAGID")));
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleTagServiceImpl.deleteArticleTag】删除文章标签失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除文章标签失败");
		}
		return rtnMap;
	}

	/**
	 * <p>Title：saveArticleTag</p> 
	 * <p>Description： 保存文章标签信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTagService#saveArticleTag(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveArticleTag(Map<String, Object> params) {
		logger.info("【ArticleTagServiceImpl.saveArticleTag】保存文章标签信息:" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
			if (Util.isEmpty(params.get("TAGID"))) {
				articleTagMapper.insertArticleTag(params);
			} else {
				articleTagMapper.updateArticleTag(params);
			}
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleTagServiceImpl.saveArticleTag】保存文章标签信息失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "保存文章标签失败");
		}
		return rtnMap;
	}

}
