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

import cn.muchen.blog.server.service.inter.ArticleTypeService;
import cn.muchen.framework.Constants;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName: ArticleTypeController 
 * @Description: 文章分类控制器
 * @author: 柯雷
 * @date：2018年11月24日 下午5:09:47
 */
@Controller
@RequestMapping("/blog")
public class ArticleTypeController {
	/**
	 * 日志打印对象
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleTypeController.class);

	@Autowired
	ArticleTypeService articleTypeServiceImpl;

	/**
	 * @Title：toFlgl 
	 * @Description：跳转到文章分类界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/flgl")
	public String toFlgl() {
		logger.info("【ArticleTypeController】跳转到文章分类界面");
		return "blog/server/articleType";
	}
	
	/**
	 * @Title：getArticleTypes 
	 * @Description：分页获取文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getArticleTypes")
	@ResponseBody
	public String getArticleTypes(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleTypeController.getArticleTypes】分页查询文章信息");
		// 返回信息
		Map<String, Object> rtnMap = articleTypeServiceImpl.getArticleTypes(params);
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
	 * @Title：deleteArticleType 
	 * @Description：删除文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteArticleType")
	@ResponseBody
	public String deleteArticleType(@RequestParam Map<String, Object> params) {
		logger.info("【ArticleTypeController.deleteArticleType】删除文章分类信息:" + params);
		return JSONObject.toJSONString(articleTypeServiceImpl.deleteArticleType(params));
	}
	
	/**
	 * @Title：saveArticleType 
	 * @Description：保存文章分类信息
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveArticleType")
	@ResponseBody
	public String saveArticleType(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【ArticleTypeController.saveArticleType】保存文章分类信息:" + params);
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("USERID", user.get("USERID"));
		return JSONObject.toJSONString(articleTypeServiceImpl.saveArticleType(params));
	}
}
