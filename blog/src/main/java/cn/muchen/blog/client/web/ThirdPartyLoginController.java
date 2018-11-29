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
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import cn.muchen.framework.Constants;
import cn.muchen.framework.service.inter.xtgl.UserService;
import cn.muchen.framework.util.HttpRequestUtil;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：ThirdPartyLoginController 
 * @Description： 第三方登录控制器
 * @author ：柯雷
 * @date ：2018年11月29日 下午1:51:45 
 *
 */
@Controller
@RequestMapping("/thirdLogin")
public class ThirdPartyLoginController {
	
	private static final String QQ_ACCESS_TOKEN = "accessToken";
    private static final String QQ_OPENID = "openid";
    
    private static final String APP_KEY_WEIBO = "2191145838";
    private static final String APP_SECRECT_WEIBO = "e8066160a6a5ab6afa7775147993465e";
    private static final String REDIRECT_URI_WEIBO = "http://22815l1b14.iask.in/thirdLogin/weiboLogin";
    
    /**
	 * @Description 用户处理对象
	 */
	@Autowired
	UserService userServiceImpl;
	
	/**
	 *  日志打印对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyLoginController.class);

	/**
	 * @throws UnsupportedEncodingException 
	 * @Title：qqLogin 
	 * @Description：qq登录成功回调函数
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("qqLogin")
	public String qqLoginCallBack(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		logger.info("【ThirdPartyLoginController.qqLoginCallBack】QQ登录成功回调函数");
		
		//注意：accessToken，openID是最重要的两个东西，要控制好
        HttpSession session = request.getSession();
        
		AccessToken accessTokenObj = null;
        String accessToken = null;
        String openID = null;
        
        try {
            // 1.发出第一次请求获取access_token
            if (session.getAttribute(QQ_ACCESS_TOKEN) != null && session.getAttribute(QQ_OPENID) != null) {
                accessToken = String.valueOf(session.getAttribute(QQ_ACCESS_TOKEN));
                openID = String.valueOf(session.getAttribute(QQ_OPENID));
            } else {
                // 用户第一次登录
                if ("".equals(accessToken)) {
                    return "framework/404";
                } else {
                    // 第一次合法登录
                    accessTokenObj = new Oauth().getAccessTokenByRequest(request);
                    accessToken = accessTokenObj.getAccessToken();
                    OpenID openIDObj = new OpenID(accessToken);
                    // 2.发出第二次请求获取openid
                    openID = openIDObj.getUserOpenID();
                    session.setAttribute("accessToken", accessToken);
                    session.setAttribute("openid", openID);
                }
            }
            UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
            // 3.发出第三次请求获取用户信息 userInfoBean
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
            
            Map<String, Object> params = new HashMap<>();
            params.put("PHOTO", userInfoBean.getAvatar().getAvatarURL100());
            params.put("USERNAME", userInfoBean.getNickname());
            params.put("LOGINID", openID);
            params.put("USERTYPE", "3");
            
            session.setAttribute("user", login(params));
        } catch (QQConnectException e) {
            logger.error("跳转到回调地址失败:", e);
            return "framework/404";
        }
        
		model.addAttribute("url", "/blog/home");
		model.addAttribute("pageId", "home");
		
		return "blog/client/index";
	}
	
	/**
	 * @Title：webLoginCallBack 
	 * @Description：TODO
	 * @param ： 
	 * @return ：void 
	 * @throws
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
			String auth_token = this.getAuthToken(code);
			Map<String, Object> resp = JSONObject.parseObject(auth_token);

			Integer errorCode = (Integer)resp.get("error_code");
			String errorMsg = (String)resp.get("error_description");
			if(errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}
			
			String accessToken = (String)resp.get("access_token");
			String uid = (String)resp.get("uid");
			
			// 用accessToken和uid换取用户信息
			String userStr = HttpRequestUtil.doGet("https://api.weibo.com/2/users/show.json?access_token=" +
					accessToken + "&uid=" + uid);
			Map<String, Object> resp2 = JSONObject.parseObject(userStr);

			errorCode = (Integer)resp2.get("error_code");
			errorMsg = (String)resp2.get("error_description");
			if(errorCode != null && errorCode != 0) {
				throw new Exception(errorMsg);
			}

			String nickname = (String)resp2.get("screen_name");
			// 微博180*180高清头像
			String avatar = (String)resp2.get("avatar_large");
			// 请求参数
			Map<String, Object> params = new HashMap<>();
			params.put("PHOTO", avatar);
            params.put("USERNAME", nickname);
            params.put("LOGINID", uid);
            params.put("USERTYPE", "5");
            
            HttpSession session = request.getSession();
            session.setAttribute("user", login(params));
            
            rtnMap.put("code", Constants.AJAX_FHZ_CG);
			rtnMap.put("message", "登录成功");
		} catch (Exception e) {
			logger.info("【ThirdPartyLoginController.webLoginCallBack】微博登录回调失败");
			rtnMap.put("code", Constants.AJAX_FHZ_SB);
			rtnMap.put("message", "登录失败：" + e.getMessage());
		}
		String html = "<script language='javaScript' type='text/javaScript'>window.opener.oRegisterBtn.loginCallBack(" + JSONObject.toJSONString(rtnMap) + ");window.close();</script>";
		return html;
	}
	
	/**
	 * @Title：getAuthToken 
	 * @Description：获取authtoken
	 * @param ：@param code
	 * @param ：@return
	 * @param ：@throws Exception 
	 * @return ：String 
	 * @throws
	 */
	private String getAuthToken(String code) throws Exception {
		logger.info("【ThirdPartyLoginController】微博登录获取authtoken：" + code);
		// 请求参数
		Map<String, Object> params1 = new HashMap<>();
		params1.put("client_id", APP_KEY_WEIBO);
		params1.put("client_secret", APP_SECRECT_WEIBO);
		params1.put("grant_type", "authorization_code");
		params1.put("redirect_uri", REDIRECT_URI_WEIBO);
		params1.put("code", code);
		return HttpRequestUtil.doPost("https://api.weibo.com/oauth2/access_token", params1);
	}
	
	/**
	 * @Title：login 
	 * @Description：第三方登录
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：Map<String,Object> 
	 * @throws
	 */
	private Map<String, Object> login(Map<String, Object> params) {
		logger.info("【ThirdPartyLoginController.login】登录：" + params);
		
		// 判断用户是否存在
		Map<String, Object> user = userServiceImpl.findUserByLoginid((String) params.get("LOGINID"));
		if (Util.isEmpty(user)) {
			// 用户不存在，则新增
			params.put("PASSWORD", "");  // 密码为空
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
