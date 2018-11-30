package cn.muchen.blog.server.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.blog.server.dao.mapper.ArticleMapper;
import cn.muchen.blog.server.service.inter.ArticleManagerService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：ArticleManagerServiceImpl 
 * @Description： 文章管理处理类
 * @author ：柯雷
 * @date ：2018年9月28日 上午10:41:58 
 *
 */
@Service
public class ArticleManagerServiceImpl implements ArticleManagerService {

	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ArticleManagerServiceImpl.class);
	
	/**
	 * @Description DAO操作对象
	 */
	@Autowired
	ArticleMapper articleMapper;
	
	@Override
	public Map<String, Object> getArticlePage(Map<String, Object> params) {
		logger.info("【ArticleManagerServiceImpl】分页查询文章信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", articleMapper.getArticleByPage(params));
		rtnMap.put("count", articleMapper.getArticleCount(params));
		return rtnMap;
	}

	/**
	 * <p>Title：deleteArticle</p> 
	 * <p>Description：删除文章信息 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleManagerService#deleteArticle(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteArticle(Map<String, Object> params) {
		logger.info("【ArticleManagerServiceImpl.deleteArticle】删除文章信息：" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			articleMapper.deleteArticle(Integer.parseInt((String) params.get("ARTICLEID")));
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleManagerServiceImpl.deleteArticle】删除文章失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除文章失败");
		}
		return rtnMap;
	}

	/**
	 * <p>Title：saveArticle</p> 
	 * <p>Description： 保存文章信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleManagerService#saveArticle(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveArticle(Map<String, Object> params) {
		logger.info("【ArticleManagerServiceImpl.saveArticle】保存文章信息:" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
			if (Util.isEmpty(params.get("ARTICLEID"))) {
				params.put("BAE002", params.get("USERID"));
				params.put("BAE004", params.get("USERID"));
				
				articleMapper.insertArticle(params);
			} else {
				params.put("BAE004", params.get("USERID"));
				
				articleMapper.updateArticle(params);
			}
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleManagerServiceImpl.saveArticle】保存文章信息失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "保存文章失败");
		}
		return rtnMap;
	}

}
