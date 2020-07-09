package com.linzx.core.framework.support.quartz.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.linzx.core.framework.support.quartz.BaseJob;
import com.linzx.core.framework.support.quartz.core.MyJobFactory;
import com.linzx.core.framework.support.quartz.entity.Job;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import java.util.List;
import java.util.Map;

/**
 * 程序启动时，初始化定时任务
 */
public class QuartzScheduleRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    private JobTemplate jobTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Job> jobRun = ListUtil.filter(jobTemplate.queryAllJob(), new Editor<Job>() {
            @Override
            public Job edit(Job job) {
                if (Job.STATUS_NORMAL.equals(job.getStatus())) {
                    return job;
                }
                return null;
            }
        });
        if( null == jobRun || jobRun.size() ==0){
            logger.info("系统启动，没有需要执行的任务... ...");
        }
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        // 如果不设置JobFactory，Service注入到Job会报空指针
        scheduler.setJobFactory(myJobFactory);
        // 启动调度器
        scheduler.start();

        for (Job job : jobRun) {
            String jobClassName = job.getJobName();
            String jobGroupName = job.getJobGroup();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(job.getClassPath()).getClass()).withIdentity(jobClassName, jobGroupName).build();
            if (StrUtil.isNotEmpty(job.getParamJson())) {
                JSONObject jb = JSONObject.parseObject(job.getParamJson());
                Map<String, Object> dataMap = (Map<String, Object>)jb.get("data");
                for (Map.Entry<String, Object> m:dataMap.entrySet()) {
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
                    logger.info("\n创建定时任务失败"+e);
                    throw new Exception("创建定时任务失败");
                }
            }
        }
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?>  c= Class.forName(classname);
        return (BaseJob)c.newInstance();
    }

}
