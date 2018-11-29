package cn.muchen.blog.client.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.muchen.blog.client.service.inter.ArticleDetailService;
import cn.muchen.blog.lucene.SearchIndex;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：ArticleDetailController 
 * @Description： 文章详情界面controller
 * @author ：柯雷
 * @date ：2018年9月20日 上午11:43:11 
 *
 */
@Controller
@RequestMapping("/blog")
public class ArticleDetailController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ArticleDetailController.class);
	
	@Autowired
	ArticleDetailService articleDetailServiceImpl;
	
	/** spring boot上下文环境 */
	@Autowired  
    private Environment environment;
	
	/**
	 * 索引检索对象
	 */
	@Autowired
	SearchIndex searchIndex;
	
	/**
	 * @Title：toArticle 
	 * @Description：跳转到文章详细界面
	 * @param ：@param ARTICLEID
	 * @param ：@param model
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/toArticle")
	public String toArticle(@RequestParam String ARTICLEID, Model model) {
		logger.info("【ArticleDetailController】跳转到文章详情列表");
		model.addAttribute("ARTICLEID", ARTICLEID);
		
		// 更新文章浏览量
		articleDetailServiceImpl.updateRead(ARTICLEID);
		return "blog/client/articleDetail";
	}
	
	/**
	 * @Title：getArticle 
	 * @Description：获取文章内容
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getArticle")
	@ResponseBody
	public String getArticle(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleDetailController】获取文章详细内容:" + params);
		return JSONObject.toJSONString(articleDetailServiceImpl.getArticleInfo((String) params.get("ARTICLEID")));
	}
	
	/**
	 * @Title：getArticeCritics 
	 * @Description：获取文章评论信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getArticeCritics")
	@ResponseBody
	public String getArticeCritics(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleDetailController】获取文章评论信息:" + params);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			rtnMap.put("CRITICS", articleDetailServiceImpl.getArticleCritic(params));
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} catch (Exception e) {
			logger.error("【ArticleDetailController.getArticeCritics】查询文章评论信息出错：错误信息为" + e);
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "初始化评论信息出错");
		}
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * @Title：doPraise 
	 * @Description：点赞或者踩
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/doPraiseOrTrade")
	@ResponseBody
	public String doPraiseOrTrade(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【ArticleDetailController】点赞或踩:" + params);
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(articleDetailServiceImpl.praisOrTrade(params));
	}
	
	/**
	 * @Title：doCritic 
	 * @Description：评论
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/doCritic")
	@ResponseBody
	public String doCritic(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【ArticleDetailController】评论:" + params);
		// 获取用户信息
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("BAE002", user.get("USERID"));
		return JSONObject.toJSONString(articleDetailServiceImpl.critic(params));
	}
	
	/**
	 * @Title：getRankArticles 
	 * @Description：获取点击量列表
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getRankArticles")
	@ResponseBody
	public String getRankArticles(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleDetailController】评论:" + params);
		return JSONObject.toJSONString(articleDetailServiceImpl.getRankArticles(params));
	}
	
	/**
	 * @Title：searchArticle 
	 * @Description：检索文章
	 * @param ：@param searchContent
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/searchArticle")
	@ResponseBody
	public String searchArticle(@RequestParam String SEARCHECONTENT) {
		logger.info("【ArticleDetailController.searchArticle】检索文章:" + SEARCHECONTENT);
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			// 返回信息
			rtnMap = searchIndex.searchArticle(SEARCHECONTENT, environment.getProperty("lucene.path"));
			if (!Util.isEmpty(rtnMap)) {  // 查询成功
				rtnMap.put("code", Constants.PAGE_QUERY_CG);
				rtnMap.put("message", "查询成功");
			} else {  // 查询失败
				rtnMap = new HashMap<>();
				rtnMap.put("code", Constants.PAGE_QUERY_SB);
				rtnMap.put("message", "未查询到结果");
			}
		} catch (Exception e) {
			logger.error("【ArticleDetailController.searchArticle】检索文章失败:" + e);
			rtnMap.put("code", Constants.PAGE_QUERY_SB);
			rtnMap.put("message", "查询文章失败");
		}
		return JSONObject.toJSONString(rtnMap);
	}
}
