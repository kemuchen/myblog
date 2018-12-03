package cn.muchen.blog.client.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.muchen.framework.Constants;
import cn.muchen.framework.service.inter.xtgl.UserService;
import cn.muchen.framework.util.HttpRequestUtil;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：ThirdPartyLoginController @Description： 第三方登录控制器
 * @author ：柯雷
 * @date ：2018年11月29日 下午1:51:45
 *
 */
@Controller
@RequestMapping("/thirdLogin")
public class ThirdPartyLoginController {

	/** 微博第三方登录信息 */
	private static final String APP_KEY_WEIBO = "2191145838";
	private static final String APP_SECRECT_WEIBO = "e8066160a6a5ab6afa7775147993465e";
	private static final String REDIRECT_URI_WEIBO = "http://22815l1b14.iask.in/thirdLogin/weiboLogin";

	/** QQ第三方登录信息 */
	private static final String APP_KEY_QQ = "101528188";
	private static final String APP_SECRECT_QQ = "7975cb8105e679e90e87d02c297dbb6a";
	private static final String REDIRECT_URI_QQ = "http://22815l1b14.iask.in/thirdLogin/qqLogin";

	/**
	 * @Description 用户处理对象
	 */
	@Autowired
	UserService userServiceImpl;

	/**
	 * 日志打印对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyLoginController.class);

	/**
	 * @throws
	 * UnsupportedEncodingException @Title：qqLogin @Description：qq登录成功回调函数 @param
	 * ：@return @return ：String @throws
	 */
	@RequestMapping("/qqLogin")
	@ResponseBody
	public String qqLoginCallBack(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		logger.info("【ThirdPartyLoginController.qqLoginCallBack】QQ登录成功回调函数");
		// 返回参数
		Map<String, Object> rtnMap = new HashMap<>();
		// 获取返回的code
		String code = request.getParameter("code");

		try {
			Map<String, Object> params = new HashMap<>();
			params.put("client_id", APP_KEY_QQ);
			params.put("client_secret", APP_SECRECT_QQ);
			params.put("grant_type", "authorization_code");
			params.put("code", code);
			params.put("redirect_uri", REDIRECT_URI_QQ);

			// 获取acess_token
			String auth_token = "{\"" + HttpRequestUtil.doGet("https://graph.qq.com/oauth2.0/token", params)
					.replaceAll("&", "\",\"").replaceAll("=", "\":\"") + "\"}";
			Map<String, Object> authMap = JSONObject.parseObject(auth_token);

			Integer errorCode = (Integer) authMap.get("code");
			String errorMsg = (String) authMap.get("msg");
			if (errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			String access_token = (String) authMap.get("access_token");

			// 获取openid
			String open_id = HttpRequestUtil.doGet("https://graph.qq.com/oauth2.0/me?access_token=" + access_token);
			open_id = open_id.substring(10, open_id.length() - 2);

			Map<String, Object> operidMap = JSONObject.parseObject(open_id);

			errorCode = (Integer) operidMap.get("code");
			errorMsg = (String) operidMap.get("msg");
			if (errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			String openid = (String) operidMap.get("openid");

			// 获取用户信息
			String user = HttpRequestUtil.doGet("https://graph.qq.com/user/get_user_info?access_token=" + access_token
					+ "&oauth_consumer_key=" + APP_KEY_QQ + "&openid=" + openid);

			Map<String, Object> qqUserMap = JSONObject.parseObject(user);

			errorCode = (Integer) qqUserMap.get("ret");
			errorMsg = (String) qqUserMap.get("msg");
			if (errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			// 请求参数
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("PHOTO", qqUserMap.get("figureurl_1"));
			userMap.put("USERNAME", qqUserMap.get("nickname"));
			userMap.put("LOGINID", openid);
			userMap.put("USERTYPE", "3");

			HttpSession session = request.getSession();
			session.setAttribute("user", login(userMap));

			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "登录成功");
		} catch (Exception e) {
			logger.info("【ThirdPartyLoginController.qqLoginCallBack】QQ登录回调失败");
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "登录失败：" + e.getMessage());
		}
		String html = "<script language='javaScript' type='text/javaScript'>window.opener.oRegisterBtn.loginCallBack("
				+ JSONObject.toJSONString(rtnMap) + ");window.close();</script>";
		return html;
	}

	/**
	 * @Title：webLoginCallBack @Description：TODO @param ： @return ：void @throws
	 */
	@RequestMapping("/weiboLogin")
	@ResponseBody
	public String weiboLoginCallBack(HttpServletRequest request) {
		logger.info("【ThirdPartyLoginController.webLoginCallBack】微博登录成功回调函数");
		// 返回参数
		Map<String, Object> rtnMap = new HashMap<>();
		// 获取返回的code
		String code = request.getParameter("code");

		try {
			// get auth_token
			Map<String, Object> params = new HashMap<>();
			params.put("client_id", APP_KEY_WEIBO);
			params.put("client_secret", APP_SECRECT_WEIBO);
			params.put("grant_type", "authorization_code");
			params.put("redirect_uri", REDIRECT_URI_WEIBO);
			params.put("code", code);

			String auth_token = HttpRequestUtil.doPost("https://api.weibo.com/oauth2/access_token", params);
			Map<String, Object> resp = JSONObject.parseObject(auth_token);

			Integer errorCode = (Integer) resp.get("error_code");
			String errorMsg = (String) resp.get("error_description");
			if (errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			String accessToken = (String) resp.get("access_token");
			String uid = (String) resp.get("uid");

			// 用accessToken和uid换取用户信息
			String userStr = HttpRequestUtil
					.doGet("https://api.weibo.com/2/users/show.json?access_token=" + accessToken + "&uid=" + uid);
			Map<String, Object> resp2 = JSONObject.parseObject(userStr);

			errorCode = (Integer) resp2.get("error_code");
			errorMsg = (String) resp2.get("error_description");
			if (errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			String nickname = (String) resp2.get("screen_name");
			String encode = Util.getEncoding(nickname);
			// 如果字符串编码是 ISO-8859-1则转换
			if ("ISO-8859-1".equals(encode)) {
				nickname = new String(nickname.getBytes(encode), "utf-8");
			}
			// 微博180*180高清头像
			String avatar = (String) resp2.get("avatar_large");
			// 请求参数
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("PHOTO", avatar);
			userMap.put("USERNAME", nickname);
			userMap.put("LOGINID", uid);
			userMap.put("USERTYPE", "5");

			HttpSession session = request.getSession();
			session.setAttribute("user", login(userMap));

			rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "登录成功");
		} catch (Exception e) {
			logger.info("【ThirdPartyLoginController.webLoginCallBack】微博登录回调失败");
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "登录失败：" + e.getMessage());
		}
		String html = "<script language='javaScript' type='text/javaScript'>window.opener.oRegisterBtn.loginCallBack("
				+ JSONObject.toJSONString(rtnMap) + ");window.close();</script>";
		return html;
	}

	/**
	 * @Title：login @Description：第三方登录 @param ：@param params @param ：@return @return
	 * ：Map<String,Object> @throws
	 */
	private Map<String, Object> login(Map<String, Object> params) {
		logger.info("【ThirdPartyLoginController.login】登录：" + params);

		// 判断用户是否存在
		Map<String, Object> user = userServiceImpl.findUserByLoginid((String) params.get("LOGINID"));
		if (Util.isEmpty(user)) {
			// 用户不存在，则新增
			params.put("PASSWORD", ""); // 密码为空
			params.put("EMAIL", "");
			params.put("TELEPHONE", "");
			params.put("STATE", "1");
			params.put("MEMO", "");
			params.put("OPERATORID", "0");
			userServiceImpl.saveUser(params);

			user = userServiceImpl.findUserByLoginid((String) params.get("LOGINID"));
		}
		return user;
	}
}
