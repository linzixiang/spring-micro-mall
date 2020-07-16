package com.linzx.admin.monitor.schedule;

import com.linzx.core.framework.support.quartz.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class TaskDemo1 extends BaseJob {

    @Override
    protected void onExecute(JobExecutionContext context) throws JobExecutionException {
        log.info("-------TaskDemo1#onExecute---------");
    }

}
