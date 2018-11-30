package cn.muchen.blog.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.blog.server.dao.mapper.ArticleTypeMapper;
import cn.muchen.blog.server.service.inter.ArticleTypeService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: ArticleTypeServiceImpl 
 * @Description: 文章分类信息处理实现类
 * @author: 柯雷
 * @date：2018年11月24日 下午5:20:57
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {
	
	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(ArticleTypeServiceImpl.class);

	@Autowired
	ArticleTypeMapper articleTypeMapper;
	
	/**
	 * <p>Title：getArticleTypes</p> 
	 * <p>Description：分页查询文章信息 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTypeService#getArticleTypes(java.util.Map)
	 */
	@Override
	public Map<String, Object> getArticleTypes(Map<String, Object> params) {
		logger.info("【ArticleTypeServiceImpl】分页查询文章分类信息，查询条件为：" + params);
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("data", articleTypeMapper.pageQueryArticleTypes(params));
		rtnMap.put("count", articleTypeMapper.articleTypeCount(params));
		return rtnMap;
	}
	
	/**
	 * <p>Title：deleteArticleType</p> 
	 * <p>Description：删除文章分类 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTypeService#deleteArticleType(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteArticleType(Map<String, Object> params) {
		logger.info("【ArticleTypeServiceImpl.deleteArticleType】删除文章分类信息：" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			articleTypeMapper.deleteArticleType(Integer.parseInt((String) params.get("TYPEID")));
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleTypeServiceImpl.deleteArticleType】删除文章分类失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除文章分类失败");
		}
		return rtnMap;
	}

	/**
	 * <p>Title：saveArticleType</p> 
	 * <p>Description： 保存文章分类信息</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.server.service.inter.ArticleTypeService#saveArticleType(java.util.Map)
	 */
	@Override
	public Map<String, Object> saveArticleType(Map<String, Object> params) {
		logger.info("【ArticleTypeServiceImpl.saveArticleType】保存文章分类信息:" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			// 判断是更新还是新增，菜单ID对应没有菜单即为新增，否则为更新
			if (Util.isEmpty(params.get("TYPEID"))) {
				articleTypeMapper.insertArticleType(params);
			} else {
				articleTypeMapper.updateArticleType(params);
			}
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleTypeServiceImpl.saveArticleType】保存文章分类信息失败：" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "保存文章分类失败");
		}
		return rtnMap;
	}
}
