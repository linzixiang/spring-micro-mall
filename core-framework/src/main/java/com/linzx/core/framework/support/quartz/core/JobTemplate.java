package com.linzx.core.framework.support.quartz.core;

import com.linzx.core.framework.support.quartz.entity.Job;

import java.util.List;

public interface JobTemplate {

    /**
     * 查询所有任务
     * @return
     */
     List<Job> queryAllJob();

}
