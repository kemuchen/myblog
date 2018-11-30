package cn.muchen.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName:：ErrorController 
 * @Description： 错误界面controller
 * @author ：柯雷
 * @date ：2018年9月6日 上午10:33:07 
 *
 */
@Controller
public class ErrorHandleController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ErrorHandleController.class);

	/**
	 * 
	 * @Title：toErrorPage 
	 * @Description：根据不同的错误码跳转到不同的错误界面
	 * @param ：@param code
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/errorPage")
	public String toErrorPage(int code) {
		logger.info("【ErrorHandleController】跳转到错误页面：code=" + code);
		String pager = "";
        switch (code) {
	        case 403:
	        	pager = "framework/403";
	        	break;
            case 404:
                pager = "framework/404";
                break;
            case 500:
                pager = "framework/500";
                break;
            case 401:
                pager = "framework/401";
                break;
            default:
            	pager = "framework/500";
        }
        return pager;
	}
}
