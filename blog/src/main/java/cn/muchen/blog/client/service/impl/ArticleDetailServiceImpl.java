package cn.muchen.blog.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.muchen.blog.client.dao.mapper.PraiseOrTradeMapper;
import cn.muchen.blog.client.service.inter.ArticleDetailService;
import cn.muchen.blog.server.dao.mapper.ArticleMapper;
import cn.muchen.blog.server.dao.mapper.CriticMapper;
import cn.muchen.framework.Constants;

/**
 * 
 * @ClassName:：ArticleDetailServiceImpl 
 * @Description： 文章详细内容界面处理类
 * @author ：柯雷
 * @date ：2018年9月29日 下午3:01:51 
 *
 */
@Service
public class ArticleDetailServiceImpl implements ArticleDetailService {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ArticleDetailServiceImpl.class);
	
	/**
	 * @Description 文章DAO操作对象
	 */
	@Autowired
	ArticleMapper articleMapper;
	
	/**
	 * @Description 评论DAO操作对象
	 */
	@Autowired
	CriticMapper criticMapper;
	
	/***
	 * @Description 点赞或踩DAO操作对象
	 */
	@Autowired
	PraiseOrTradeMapper praiseOrTradeMapper;

	/**
	 * <p>Title：getArticleInfo</p> 
	 * <p>Description：获取文章详细内容界面信息：文章信息 </p> 
	 * @param articleId
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#getArticleInfo(java.lang.String)
	 */
	@Override
	public Map<String, Object> getArticleInfo(String articleId) {
		logger.info("【ArticleDetailServiceImpl】查询文章详细内容:" + articleId);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			rtnMap.put("data", articleMapper.getArticleById(Integer.parseInt(articleId)));
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleDetailServiceImpl.getArticleInfo】查询文章详细内容出错:" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "查询文章内容出错");
		}
		return rtnMap;
	}
	
	/**
	 * <p>Title：getArticleCritic</p> 
	 * <p>Description： 获取文章评论信息</p> 
	 * @param articleId
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#getArticleCritic(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getArticleCritic(Map<String, Object> params) {
		logger.info("【ArticleDetailServiceImpl.getArticleCritic】获取文章评论信息");
		
		// 查询条件
		params.put("PARENTID", "0");    // 上级评论id
		params.put("VALIDATED", "1");   // 有效标志
		
		// 获取一级评论
		List<Map<String, Object>> critics = criticMapper.getCritics(params);
		
		// 遍历一级评论获取子评论
		for (Map<String, Object> critic : critics) {
			params.put("PARENTID", critic.get("CRITICID"));  // 设置上级评论为次级评论id
			critic.put("SUBCRITIC", criticMapper.getCritics(params));
		}
		
		return critics;
	}

	/**
	 * <p>Title：praisOrTrade</p> 
	 * <p>Description：点赞或者踩 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#praisOrTrade(java.util.Map)
	 */
	@Override
	public Map<String, Object> praisOrTrade(Map<String, Object> params) {
		logger.info("【ArticleDetailServiceImpl.praisOrTrade】新增点赞或踩信息");
		/** 点赞或踩 */
		String PRAORTRE = (String) params.get("PRAORTRE");
		/** 文章或评论 */
		String ARTORCRI = (String) params.get("ARTORCRI");
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		try {
			// 判断是否做过相同操作
			Integer count = praiseOrTradeMapper.getCount(params);
			// 控制不能重复赞或踩
			if (count != 0) {
				rtnMap.put("code", Constants.AJAX_FHZ_SB);
				if ("1".equals(PRAORTRE)) {
					rtnMap.put("message", "您已赞过，谢谢欣赏");
				} else {
					rtnMap.put("message", "您已踩过，请手下留情");
				}
			} else {
				// 新增点赞信息
				praiseOrTradeMapper.insert(params);
				
				if ("1".equals(PRAORTRE)) {
					// 点赞设置点赞数量
					params.put("PRAISECOUNT", "PRAISECOUNT");
				} else {
					// 踩设置踩数量
					params.put("TREADCOUNT", "TREADCOUNT");
				}

				if ("1".equals(ARTORCRI)) {
					params.put("ARTICLEID", params.get("ARID"));
					// 更新文章点赞或踩数量
					articleMapper.updateArticle(params);
				} else {
					params.put("CRITICID", params.get("ARID"));
					// 更新评论点赞或踩数量
					criticMapper.updateCritic(params);
				}
				rtnMap.put("code", Constants.AJAX_FHZ_CG);
				rtnMap.put("message", "");
			}
		} catch (Exception e) {
			logger.error("【ArticleDetailServiceImpl.praisOrTrade】新增点赞或踩信息出错:" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "操作出错");
		}
		return rtnMap;
	}

	/**
	 * <p>Title：critic</p> 
	 * <p>Description：评论文章或者回复评论 </p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#critic(java.util.Map)
	 */
	@Override
	public Map<String, Object> critic(Map<String, Object> params) {
		logger.info("【ArticleDetailServiceImpl.critic】新增文章评论信息");
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			// 新增评论
			criticMapper.insertCritic(params);
			// 文章评论数量加1
			params.put("CONTENT", "");  // 设置内容为空，避免更新文章内容
			params.put("CRITICCOUNT", "CRITICCOUNT");
			articleMapper.updateArticle(params);
			
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleDetailServiceImpl.critic】新增文章评论出错:" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "新增文章评论出错");
		}
		return rtnMap;
	}

	/**
	 * <p>Title：updateRead</p> 
	 * <p>Description： 更新文章浏览量</p> 
	 * @param ARTICLEID
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#updateRead(java.lang.String)
	 */
	@Override
	public Integer updateRead(String ARTICLEID) {
		logger.info("【ArticleDetailServiceImpl.updateRead】更新文章浏览量");
		Map<String, Object> params = new HashMap<>();
		params.put("ARTICLEID", ARTICLEID);
		params.put("READCOUNT", "READCOUNT");
		return articleMapper.updateArticle(params);
	}

	/**
	 * <p>Title：getRankArticles</p> 
	 * <p>Description： 根据点击量获取文章列表</p> 
	 * @param params
	 * @return 
	 * @see cn.muchen.blog.client.service.inter.ArticleDetailService#getRankArticles(java.util.Map)
	 */
	@Override
	public Map<String, Object> getRankArticles(Map<String, Object> params) {
		logger.info("【ArticleDetailServiceImpl.getRankArticles】根据文章点击量获取文章排名列表");
		/** return Map */
		Map<String, Object> rtnMap = new HashMap<>();
		
		rtnMap.put("code", Constants.AJAX_FHZ_CG);
		rtnMap.put("message", "");
		rtnMap.put("data", articleMapper.getRankArticles(params));
		return rtnMap;
	}
}
