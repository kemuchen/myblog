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

import cn.muchen.blog.server.service.inter.ArticleTagService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: ArticleTagController 
 * @Description: 文章标签控制器
 * @author: 柯雷
 * @date：2018年11月24日 下午5:09:47
 */
@Controller
@RequestMapping("/blog")
public class ArticleTagController {
	/**
	 * 日志打印对象
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleTagController.class);

	@Autowired
	ArticleTagService articleTagServiceImpl;

	/**
	 * @Title：toBqgl
	 * @Description：跳转到文章标签界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/bqgl")
	public String toBqgl() {
		logger.info("【ArticleTagController】跳转到文章标签界面");
		return "blog/server/articleTag";
	}
	
	/**
	 * @Title：getArticleTags 
	 * @Description：分页获取文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getArticleTags")
	@ResponseBody
	public String getArticleTags(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleTagController.getArticleTags】分页查询文章信息");
		// 返回信息
		Map<String, Object> rtnMap = articleTagServiceImpl.getArticleTags(params);
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
	 * @Title：deleteArticleTag 
	 * @Description：删除文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteArticleTag")
	@ResponseBody
	public String deleteArticleTag(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleTagController.deleteArticleTag】删除文章标签信息:" + params);
		return JSONObject.toJSONString(articleTagServiceImpl.deleteArticleTag(params));
	}
	
	/**
	 * @Title：saveArticleTag 
	 * @Description：保存文章标签信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveArticleTag")
	@ResponseBody
	public String saveArticleTag(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【ArticleTagController.saveArticleTag】保存文章标签信息:" + params);
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(articleTagServiceImpl.saveArticleTag(params));
	}
}