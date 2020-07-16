package com.linzx.core.framework.support.quartz.util;

import cn.hutool.core.collection.ListUtil;
import com.linzx.core.framework.support.quartz.BaseJob;
import com.linzx.core.framework.support.quartz.core.Job;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 定时任务工具类
 */
@Component
public class ScheduleUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleUtils.class);

    private static Scheduler scheduler;

    public ScheduleUtils(Scheduler scheduler) {
        ScheduleUtils.scheduler = scheduler;
    }

    /**
     * 新增任务
     * @param job
     */
    public static void addJob(Job job) throws Exception {
        ScheduleUtils.addJobs(ListUtil.toList(job));
    }

    public static void addJobs(List<Job> jobList) throws Exception {
        for (Job job : jobList) {
            String jobClassName = job.getJobName();
            String jobGroupName = job.getJobGroup();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(job.getClassPath()).getClass()).withIdentity(jobClassName, jobGroupName).build();
            if (job.getParamJson() != null) {
                for (Map.Entry<String, Object> m : job.getParamJson().entrySet()) {
                    jobDetail.getJobDataMap().put(m.getKey(),m.getValue());
                }
            }
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                    .withSchedule(scheduleBuilder).startNow().build();
            // 任务不存在的时候才添加
            if( !scheduler.checkExists(jobDetail.getKey()) ){
                try {
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    logger.error("\n创建定时任务失败"+e);
                    throw new Exception("创建定时任务失败");
                }
            }
        }
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?>  c= Class.forName(classname);
        return (BaseJob)c.newInstance();
    }

    /**
     * 停用一个定时任务
     * @param jobName 任务名称
     * @param jobGroupName 组别
     * @throws Exception
     */
    public static void pauseJob(String jobName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 启用一个定时任务
     * @param jobName 任务名称
     * @param jobGroupName 组别
     * @throws Exception
     */
    public static void resumeJob(String jobName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 删除一个定时任务
     * @param jobName 任务名称
     * @param jobGroupName 组别
     * @throws Exception
     */
    public static void deleteJob(String jobName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 更新定时任务表达式
     * @param jobName 任务名称
     * @param jobGroupName 组别
     * @param cronExpression Cron表达式
     * @throws Exception
     */
    public static void rescheduleJob(String jobName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).startNow().build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 检查Job是否存在
     * @throws Exception
     */
    public static Boolean isJobExist(String jobName, String jobGroupName) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        Boolean state = scheduler.checkExists(triggerKey);
        return state;
    }

    /**
     * 暂停所有任务
     * @throws Exception
     */
    public static void pauseAlljob() throws Exception {
        scheduler.pauseAll();
    }

    /**
     * 唤醒所有任务
     * @throws Exception
     */
    public static void resumeAlljob() throws Exception {
        scheduler.resumeAll();
    }


}
