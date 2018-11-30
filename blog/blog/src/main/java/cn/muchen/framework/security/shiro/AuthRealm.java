package cn.muchen.framework.security.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.muchen.framework.service.inter.xtgl.UserService;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：AuthRealm 
 * @Description： shiro框架验证
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:46:58 
 *
 */
public class AuthRealm extends AuthorizingRealm {

	/** 
	 * @Description 日志打印对象
	 */ 
	private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);
	
	/** 
	 * @Description 用户数据处理类对象  
	 */
	@Autowired
	UserService userServiceImpl;
	
	/**
	 * 
	 * <p>Title：doGetAuthorizationInfo</p> 
	 * <p>Description：  权限认证</p> 
	 * @param principals
	 * @return 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/** 用户信息，包括权限 */
		Map<String, Object> user = (Map<String, Object>) principals.fromRealm(this.getClass().getName()).iterator().next();
		
		/** 用户权限列表 */
		List<Map<String, Object>> rightis = (List<Map<String, Object>>) user.get("RIGHTIS");
		List<String> permissions = new ArrayList<>();
		
		/** 遍历菜单列表，获取权限url */
		for (Map<String, Object> right : rightis) {
			List<Map<String, Object>> childs = (List<Map<String, Object>>) right.get("CHILDREN");
			for (Map<String, Object> child : childs) {
				permissions.add((String) child.get("URL"));
			}
		}
		
		// 增加首页权限
		permissions.add("/index");
		permissions.add("/index/main");
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
		return info;
	}

	/**
	 * 
	 * <p>Title：doGetAuthenticationInfo</p> 
	 * <p>Description：登录认证 </p> 
	 * @param token
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        logger.info("【user auth】用户登录认证：username=" + username);
        Map<String, Object> user = userServiceImpl.findUserByLoginid(username);
        /** 查询用户为空  */
        if (Util.isEmpty(user)) {
        	throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.get("PASSWORD"), this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
	}

}
