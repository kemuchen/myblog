package cn.muchen.framework.web.xtgl;

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
import cn.muchen.framework.Constants;
import cn.muchen.framework.service.inter.xtgl.UserService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：UserController 
 * @Description： 用户管理控制器
 * @author ：柯雷
 * @date ：2018年9月10日 下午1:59:08 
 *
 */
@Controller
@RequestMapping("/xtgl")
public class UserController {
	
	/**
	 * @Description 日志打印对象
	 */
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * @Description 用户处理对象
	 */
	@Autowired
	UserService userServiceImpl;
	
	/**
	 * 
	 * @Title：toUser 
	 * @Description：跳转到用户管理界面
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/user")
	public String toUser() {
		logger.info("【UserController】跳转到用户管理界面");
		return "framework/xtgl/user";
	}
	
	/**
	 * 
	 * @Title：getAllUsers 
	 * @Description：分页查询用户信息
	 * @param ：@param params
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getAllUsers")
	@ResponseBody
	public String getAllUsers(@RequestParam Map<String, Object> params) {
		logger.info("【UserController.getAllUsers】分页查询用户信息，查询条件为：" + params);
		
		// 返回信息
		Map<String, Object> rtnMap = userServiceImpl.getUsersByPage(params);
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
	 * @Title：saveUser 
	 * @Description：保存用户信息
	 * @param ：@param params
	 * @param ：@param session
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/saveUser")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String saveUser(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【UserController.saveUser】保存用户信息:" +params);
		
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("OPERATORID", user.get("USERID"));
		return JSONObject.toJSONString(userServiceImpl.saveUser(params));
	}
	
	/**
	 * 
	 * @Title：deleteUser 
	 * @Description：删除用户信息
	 * @param ：@param params
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam Map<String, Object> params) {
		logger.info("【UserController.deleteUser】删除用户信息");
		// 返回map
		Map<String, Object> rtnMap = new HashMap<>();
		if (userServiceImpl.deleteUser(Integer.parseInt((String) params.get("USERID")))) {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} else {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "删除角色失败");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：getUserUnAuthorizes 
	 * @Description：查询用户没有的角色信息
	 * @param ：@param params
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getUserUnAuthorizes")
	@ResponseBody
	public String getUserUnAuthorizes(@RequestParam Map<String, Object> params) {
		logger.info("【UserController.getUserUnAuthorizes】分页查询用户没有的角色信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = userServiceImpl.getUserUnAuthorizeByPag(params);
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
	 * @Title：getUserAuthorizes 
	 * @Description：查询用户已有的角色信息
	 * @param ：@param params
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getUserAuthorizes")
	@ResponseBody
	public String getUserAuthorizes(@RequestParam Map<String, Object> params) {
		logger.info("【UserController.getUserUnAuthorizes】分页查询用户已有的角色信息，查询条件为：" + params);
		// 返回信息
		Map<String, Object> rtnMap = userServiceImpl.getAuthorizeByPage(params);
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
	 * @Title：authorizeUserRole 
	 * @Description：用户授权
	 * @param ：@param params
	 * @param ：@param session
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/authorizeUserRole")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String authorizeUserRole(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【UserController.authorizeUserRole】保存用户授权:" + params);
		
		// 获取用户信息
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		params.put("OPERATOR", user.get("USERID"));
		return JSONObject.toJSONString(userServiceImpl.authorizeUserRole(params));
	}
	
	/**
	 * 
	 * @Title：unAuthorizeUserRole 
	 * @Description：取消用户授权
	 * @param ：@param params
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/unAuthorizeUserRole")
	@ResponseBody
	public String unAuthorizeUserRole(@RequestParam Map<String, Object> params) {
		logger.info("【UserController.unAuthorizeRole】取消用户授权：" + params);
		// 返回map
		Map<String, Object> rtnMap = new HashMap<>();
		if (userServiceImpl.unAuthorizeUserRole(params)) {
			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "");
		} else {
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "取消授权失败");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * 
	 * @Title：register 
	 * @Description：用户注册
	 * @param ：@param params
	 * @param ：@param session
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(@RequestParam Map<String, Object> params, HttpSession session) {
		logger.info("【UserController.register】用户注册：params=" + params);
        
        /* 登录返回信息 */
        Map<String, Object> rtnMap = new HashMap<>();
        
		if (!session.getAttribute("validateCode").equals(params.get("validateCode").toString().toUpperCase())) {
    		rtnMap.put("code", Constants.AJAX_FHZ_SB);
            rtnMap.put("message", "验证码不正确");
		} else {
			// 保存用户信息
			Map<String, Object> user = new HashMap<>();
			user.put("USERNAME", params.get("username"));
			user.put("LOGINID", params.get("email"));
			user.put("PASSWORD", params.get("password"));
			user.put("USERTYPE", "2");
			user.put("EMAIL", params.get("email"));
			user.put("TELEPHONE", "");
			user.put("STATE", "1");
			user.put("PHOTO", params.get("photo"));
			user.put("MEMO", "");
			user.put("OPERATORID", "0");
			rtnMap = userServiceImpl.saveUser(user);
			
			session.setAttribute("user", userServiceImpl.findUserByLoginid((String) params.get("email")));
		}
        return JSONObject.toJSONString(rtnMap);
	}
	
	/**
	 * @Title：getUserByLoginid 
	 * @Description：根据登录id查询用户
	 * @param ：@param loginid
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/getUserByLoginid")
	@ResponseBody
	public String getUserByLoginid(String loginid) {
		logger.info("【UserController.getUserByLoginid】根据登录id查询用户");
		Map<String, Object> rtnMap = new HashMap<>();
		
		try {
			Map<String, Object> tempUser = userServiceImpl.findUserByLoginid(loginid);
			if (!Util.isEmpty(tempUser)) {
				rtnMap.put("code", Constants.AJAX_FHZ_CG);
				rtnMap.put("message", "");
				rtnMap.put("user", tempUser);
			} else {
				rtnMap.put("code", Constants.AJAX_FHZ_SB);
				rtnMap.put("message", "用户不存在");
			}
		} catch (Exception e) {
			logger.info("【UserController.getUserByLoginid】根据登录id查询用户失败：" + e);
			rtnMap.put("code", "-99999");
			rtnMap.put("message", "服务器异常");
		}
		
		return JSONObject.toJSONString(rtnMap);
	}
}
