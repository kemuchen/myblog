package cn.muchen.framework.security.runner;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import cn.muchen.framework.dao.mapper.CommonMapper;
import cn.muchen.framework.util.quartz.QuartzScheduler;

/**
 * 
 * @ClassName:：QuartScheduleRunner 
 * @Description： 定时器启动类
 * @author ：柯雷
 * @date ：2018年11月16日 下午4:54:04 
 *
 */
@Component //被spring容器管理
@Order(1) //如果多个自定义ApplicationRunner，用来标明执行顺序
public class QuartScheduleRunner implements ApplicationRunner {
	
	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(QuartScheduleRunner.class);

	/** spring boot上下文环境 */
	@Autowired  
    private Environment environment;
	
	@Autowired
	CommonMapper commonMapper;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if ("true".equals(environment.getProperty("app.timer"))) {
			logger.info("启动定时器");
			
			List<Map<String, Object>> quartzs = commonMapper.getAllQuartas();
			
			for(Map<String, Object> quartz : quartzs) {
				@SuppressWarnings("unchecked")
				Class<? extends Job> clazz = (Class<? extends Job>) Class.forName((String) quartz.get("TASK"));
				QuartzScheduler.doSchedule(clazz, (String)quartz.get("JOBID"), (String)quartz.get("SCHEDULE"), new Date());
			}
		}
	}
}
