package com.linzx.core.framework.support.quartz.core;

import com.linzx.core.framework.support.quartz.util.ScheduleUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * 程序启动时，初始化定时任务
 */
public class QuartzScheduleRunner implements CommandLineRunner, SchedulerFactoryBeanCustomizer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    private QuartzJobInit quartzJobInit;

    @Override
    public void run(String... args) throws Exception {
        List<Job> initJobList = quartzJobInit.initJob();
        if( null == initJobList || initJobList.size() ==0){
            logger.info("系统启动，没有需要执行的任务... ...");
        }
        ScheduleUtils.addJobs(initJobList);
    }

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setJobFactory(myJobFactory);
    }
}
