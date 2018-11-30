package cn.muchen.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import cn.muchen.framework.security.error.ErrorPageInterceptor;

/**
 * 
 * @ClassName:：WebMvcConfig 
 * @Description： WebMvc配置
 * @author ：柯雷
 * @date ：2018年9月6日 上午11:03:55 
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * @Description 错误页面拦截去
	 */
	@Autowired
	ErrorPageInterceptor errorPageInterceptor;
	
	/**
	 * 
	 * @Title：addInterceptors 
	 * @Description：添加拦截器
	 * @param ：@param registry 
	 * @return ：void 
	 * @throws
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor).addPathPatterns("/*");  // 拦截所有
        super.addInterceptors(registry);
    }
	
	/**
	 * <p>Title：addResourceHandlers</p> 
	 * <p>Description：配置静态资源访问 </p> 
	 * @param registry 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
	}
}
