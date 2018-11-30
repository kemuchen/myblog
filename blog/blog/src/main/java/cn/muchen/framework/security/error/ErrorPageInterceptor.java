package cn.muchen.framework.security.error;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName:：ErrorPageInterceptor 
 * @Description： 错误页面拦截器
 * @author ：柯雷
 * @date ：2018年9月6日 上午10:50:48 
 *
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(ErrorPageInterceptor.class);
	
	/**
	 * @Description 错误值列表
	 */
	private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 401);
	
	/**
	 * @Description 错误值列表
	 */
	private List<String> errorMsgList = Arrays.asList("访问地址不存在", "禁止访问", "服务器异常", "无权进行此操作");
	
	/**
	 * 
	 * <p>Title：preHandle</p> 
	 * <p>Description：拦截器处理 </p> 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {
    	if (errorCodeList.contains(response.getStatus())) {
    		logger.info("【ErrorPageInterceptor】请求页面" + request.getRequestURI() + "错误，错误代码为：" + response.getStatus());
    		String requestType = request.getHeader("X-Requested-With");
    		if (!"XMLHttpRequest".equals(requestType)) { // 如果是普通请求，则返回错误页面；如果是ajax请求，则返回提示信息
    			response.sendRedirect("/errorPage?code=" + response.getStatus());
    		} else {
    			response.setCharacterEncoding("UTF-8");
    			PrintWriter out = response.getWriter();
    			Map<String, Object> rtnMap = new HashMap<>();
    			
    			rtnMap.put("code", "-99");
    			rtnMap.put("message", errorMsgList.get(errorCodeList.indexOf(response.getStatus())));
    			
    			out.print(JSONObject.toJSONString(rtnMap));
    			response.setStatus(200);
    			out.close();
    		}
    		return false;
    	}
    	return super.preHandle(request, response, handler);
    }
}
