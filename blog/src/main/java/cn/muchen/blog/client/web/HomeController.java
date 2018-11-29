package cn.muchen.blog.client.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.muchen.framework.util.Util;

@Controller
@RequestMapping("/blog")
public class HomeController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	 * @Title：toIndex 
	 * @Description：跳转到博客前台主页
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping(value = "/index/{pageId}")
	public String toIndex(Model model, @PathVariable String pageId) throws UnsupportedEncodingException {
		logger.info("【HomeController】跳转到博客前台首页");
		String url = "";
		if (Util.isEmpty(pageId)) {
			url = "/blog/home";
		} else if (pageId.startsWith("article")) {
			// 跳转到文章详情界面
			url = URLDecoder.decode("/blog/toArticle?ARTICLEID=" + pageId.substring(7), "utf-8");
		} else {
			url = URLDecoder.decode("/blog/" + pageId, "utf-8");
		}
		model.addAttribute("url", url);
		model.addAttribute("pageId", pageId);
		return "blog/client/index";
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @Title：toRegisterOrLogin 
	 * @Description：跳转到注册或登录界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/toRegisterOrLogin")
	public String toRegisterOrLogin(Model model, String url) throws UnsupportedEncodingException {
		logger.info("【HomeController】跳转到注册或登录界面");
		if (Util.isEmpty(url)) {
			url = "/blog/home";
		} else {
			url = URLDecoder.decode(url, "utf-8");
		}
		model.addAttribute("url", url);
		return "blog/client/register";
	}
	
	/**
	 * 
	 * @Title：toIndex 
	 * @Description：跳转到博客前台主页
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/home")
	public String toHome() {
		logger.info("【HomeController】跳转到博客前台首页主内容区域");
		return "blog/client/home";
	}
	
	/**
	 * @Title：toRegister 
	 * @Description：跳转到注册界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/register")
	public String toRegister() {
		logger.info("【HomeController】跳转到博客前台首页注册界面");
		return "blog/client/register";
	}
	
	/**
	 * @Title：toSearch 
	 * @Description：跳转到前台搜索界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/search")
	public String toSearch() {
		logger.info("【HomeController】跳转到博客前台搜索界面");
		return "blog/client/search";
	}
}