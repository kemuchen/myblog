package cn.muchen.framework.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.muchen.framework.Constants;

/**
 * 
 * @ClassName:：LoginController 
 * @Description： 登录controller
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:13:09 
 *
 */
@Controller
@RequestMapping("/xtgl")
public class LoginController {
	
	/** 
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@RequestMapping("/toLogin")
	public void toLogin(ServletRequest request, ServletResponse response) throws IOException {
		logger.info("【LoginController.toLogin】跳转到登录界面");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String requestedWith = httpServletRequest.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(requestedWith)){
			Map<String, Object> rtnMap = new HashMap<>();
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "请先登录");
			
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.getWriter().write(JSONObject.toJSONString(rtnMap));
		} else {
			// 不是ajax进行重定向处理
			httpServletResponse.sendRedirect("/xtgl/redirectLogin");
		}
	}
	
	/** 
	 * @Title：toLogin 
	 * @Description：登录
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/redirectLogin")
	public String redirectLogin() {
		logger.info("【login】跳转到登录界面");
		return "framework/login";
	}
	
	/**
	 * @Title：login 
	 * @Description：登录
	 * @param ：@param username:用户名
	 * @param ：@param password:密码
	 * @param ：@param session:httpsession
	 * @return ：String 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, String validateCode, HttpSession session) {
		logger.info("【user login】用户登录：username=" + username + ";password=" + password);
		String fhz = Constants.AJAX_FHZ_CG;
        String errMsg = "";
        

        /* 登录返回信息 */
        Map<String, Object> rtnMap = new HashMap<>();
        
		if (!session.getAttribute("validateCode").equals(validateCode.toUpperCase())) {
			fhz = Constants.AJAX_FHZ_SB;
        	errMsg = "验证码不正确";
		} else {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
	        Subject subject = SecurityUtils.getSubject();
	        try {
	            subject.login(usernamePasswordToken);   // 完成登录
	            
				Map<String, Object> user = (Map<String, Object>) subject.getPrincipal();
	            session.setAttribute("user", user);
	        } catch (UnknownAccountException uae) {
	        	fhz = Constants.AJAX_FHZ_SB;
	        	errMsg = "用户不存在或密码错误";
	        } catch (IncorrectCredentialsException ice) {
	        	fhz = Constants.AJAX_FHZ_SB;
	        	errMsg = "用户不存在或密码错误";
	        } catch (LockedAccountException lae) {
	        	fhz = Constants.AJAX_FHZ_SB;
	        	errMsg = "用户被锁定";
	        } catch (AuthenticationException ae) {
	        	fhz = Constants.AJAX_FHZ_SB;
	        	errMsg = "登录错误，请稍后重试";
	        }
		}
		rtnMap.put("code", fhz);
        rtnMap.put("message", errMsg);
        return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * @Title：logOut 
	 * @Description：退出登录
	 * @param ：@param session
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/logout")
    public String logOut(HttpSession session) {
		logger.info("【logout】退出登录");
		session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "framework/login";
    }
}
