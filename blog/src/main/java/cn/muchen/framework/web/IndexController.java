package cn.muchen.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:：IndexController 
 * @Description： 登录成功后首页
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:43:19 
 *
 */
@Controller
@RequestMapping("/xtgl")
public class IndexController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * @Title：index 
	 * @Description：主页
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/index")
	public String index() {
		logger.info("【index】登录成功跳转到首页");
		return "framework/index";
	}
	
	/**
	 * @Title：main 
	 * @Description：主页中主区域
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/index/main")
	public String main() {
		logger.info("【index】进入首页加载中间主区域");
		return "framework/main";
	}
}
