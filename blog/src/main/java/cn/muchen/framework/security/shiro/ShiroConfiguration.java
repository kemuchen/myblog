package cn.muchen.framework.security.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.muchen.framework.service.inter.xtgl.RightService;
import cn.muchen.framework.util.Util;
import org.apache.shiro.mgt.SecurityManager;

/**
 * 
 * @ClassName:：ShiroConfiguration 
 * @Description： shiro框架配置
 * @author ：柯雷
 * @date ：2018年9月5日 下午5:03:43 
 *
 */
@Configuration
public class ShiroConfiguration {
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager, RightService rightServiceImpl) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/xtgl/toLogin");
		bean.setSuccessUrl("/xtgl/index");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		
		// 遍历所有权限配置访问控制
		List<Map<String, Object>> rights = rightServiceImpl.getAllRight();
		for (Map<String, Object> right : rights) {
			// 权限url不为空
			if (!Util.isEmpty(right.get("URL"))) {
				if ("1".equals(right.get("AUTHED"))) {  // 需要认证
					filterChainDefinitionMap.put((String) right.get("URL"), "authc, perms[" + right.get("URL") + "]");//表示需要认证才可以访问
				}
			}
		}
		
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

	// 配置自定义的权限登录器
	@Bean(name = "authRealm")
	public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}

	// 配置自定义的密码比较器
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		return new CredentialsMatcher();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
}
