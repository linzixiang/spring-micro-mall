package com.linzx.core.framework.support.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 自定义任务继承此类
 */
public abstract class BaseJob implements Job {

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        before(context);
        onExecute(context);
        after(context);
    }

    protected abstract void onExecute(JobExecutionContext context)  throws JobExecutionException;

    protected void before(JobExecutionContext context) {
        threadLocal.set(new Date());
    }



    protected void after(JobExecutionContext context) {
        // 记录日志
    }


}
