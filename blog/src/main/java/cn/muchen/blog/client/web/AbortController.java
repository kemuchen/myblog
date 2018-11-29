package cn.muchen.blog.client.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: AbortController 
 * @Description: 关于控制器
 * @author: 柯雷
 * @date：2018年11月26日 下午7:41:01
 */
@Controller
@RequestMapping("/blog")
public class AbortController {
	
	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(AbortController.class);

	/**
	 * @Title：toAbort 
	 * @Description： 跳转到关于界面
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/about")
	public String toAbort() {
		logger.info("【AbortController.toAbort】跳转到关于界面");
		return "blog/client/abort";
	}
}
