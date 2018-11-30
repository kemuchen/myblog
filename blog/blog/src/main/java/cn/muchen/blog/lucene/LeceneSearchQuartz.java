package cn.muchen.blog.lucene;

import java.io.IOException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import cn.muchen.framework.util.SpringUtil;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：LeceneSearchQuartz 
 * @Description： TODO
 * @author ：柯雷
 * @date ：2018年11月27日 下午2:44:20 
 *
 */
public class LeceneSearchQuartz implements Job {

	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(LeceneSearchQuartz.class);
	
	/** spring boot上下文环境 */
    private Environment environment = (Environment) SpringUtil.getBean("environment");
	
	/**
	 * 索引生成对象
	 */
	GeneratorIndex generatorIndex = (GeneratorIndex) SpringUtil.getBean("generatorIndex");

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey key = context.getJobDetail().getKey();
		// 打印当前执行时间
		logger.info("【LeceneSearchQuartz】执行定时器：" + key.getName() + ";开始时间" + Util.getDqrq("yyyy-MM-dd HH:mm:ss"));

		try {
			generatorIndex.genIndex(environment.getProperty("lucene.path"));
			// 打印当前执行时间
			logger.info("【LeceneSearchQuartz】执行定时器：" + key.getName() + "成功;结束时间" + Util.getDqrq("yyyy-MM-dd HH:mm:ss"));
		} catch (IOException e) {
			logger.error("【LeceneSearchQuartz】执行定时器：" + key.getName() + "失败；失败原因：" + e);
		}
	}
}
