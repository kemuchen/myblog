package cn.muchen.blog.client.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName:：TimeLineController 
 * @Description： 时间线
 * @author ：柯雷
 * @date ：2018年9月17日 上午10:16:15 
 *
 */
@Controller
@RequestMapping("/blog")
public class TimeLineController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(TimeLineController.class);
	
	@RequestMapping("/time")
	public String toTimeLine() {
		logger.info("【TimeLineController】跳转到时间线界面");
		return "blog/client/timeline";
	}
}
