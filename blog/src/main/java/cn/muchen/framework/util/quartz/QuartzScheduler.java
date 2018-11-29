package cn.muchen.framework.util.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import java.util.Date;

/**
 * 
 * @ClassName:：QuartzScheduler @Description： 定时任务调度器
 * @author ：柯雷
 * @date ：2018年11月16日 下午4:33:09
 *
 */
public class QuartzScheduler {

	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(QuartzScheduler.class);

	/**
	 * @Title：doSchedule 
	 * @Description：开始定时调度
	 * @param ：@param clazz 定时任务
	 * @param ：@param jobId 任务id
	 * @param ：@param intervalInMillis 执行时间间隔
	 * @param ：@throws SchedulerException 
	 * @return ：void 
	 * @throws
	 */
	public static void doSchedule(Class<? extends Job> clazz, String jobId, String schedule, Date triggerStartTime) throws SchedulerException {
		logger.info("开启定时任务：" + jobId);
		
		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobId, "quartz").build();

		// 创建一个Trigger实例，定义该Job在triggerStartTime时间点执行，并且后续按照schedule永远执行
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobId, "quartz").startAt(triggerStartTime)
				.withSchedule(cronSchedule(schedule)).build();
		
		// 创建scheduler实例
		SchedulerFactory sFactory = new StdSchedulerFactory();
		Scheduler scheduler = sFactory.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
