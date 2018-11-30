package cn.muchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @ClassName:：SpringBootBlogApplication 
 * @Description：SpringBoot 启动入口
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:44:31 
 *
 */
@SpringBootApplication
@MapperScan("cn.muchen.**.dao.mapper.**")
@ComponentScan(basePackages = { "cn.muchen"})
@EnableCaching
public class SpringBootBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApplication.class, args);
	}
}
