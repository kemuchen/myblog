package cn.muchen.blog.server.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.muchen.blog.server.service.inter.ArticleManagerService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：ArticleManagerController 
 * @Description： 文章管理
 * @author ：柯雷
 * @date ：2018年9月20日 下午2:27:21 
 *
 */
@Controller
@RequestMapping("/blog")
public class ArticleManagerController {

	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ArticleManagerController.class);
	
	@Autowired
	ArticleManagerService articleManagerServiceImpl;
	
	@RequestMapping("/articleManager")
	public String toArticleManager() {
		logger.info("【ArticleManagerController】跳转到文章管理界面");
		return "blog/server/articleManager";
	}
	
	
	/**
	 * 
	 * @Title：getAllArticles 
	 * @Description：分页查询文章列表
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getAllArticles")
	@ResponseBody
	public String getAllArticles(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleManagerController.getAllArticles】查询文章列表：" + params);
		// 返回信息
		Map<String, Object> rtnMap = articleManagerServiceImpl.getArticlePage(params);
		if (!Util.isEmpty(rtnMap)) {  // 查询成功
			rtnMap.put("code", Constants.PAGE_QUERY_CG);
			rtnMap.put("message", "查询成功");
		} else {  // 查询失败
			rtnMap = new HashMap<>();
			rtnMap.put("code", Constants.PAGE_QUERY_SB);
			rtnMap.put("message", "查询失败");
		}
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：saveArticle 
	 * @Description：保存文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveArticle")
	@ResponseBody
	public String saveArticle(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【ArticleManagerController.saveArticle】保存文章信息");
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(articleManagerServiceImpl.saveArticle(params));
	}
	
	/**
	 * 
	 * @Title：deleteArticle 
	 * @Description：删除文章信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteArticle")
	@ResponseBody
	public String deleteArticle(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleManagerController.deleteArticle】删除文章信息:" + params);
		return JSONObject.toJSONString(articleManagerServiceImpl.deleteArticle(params));
	}
}
