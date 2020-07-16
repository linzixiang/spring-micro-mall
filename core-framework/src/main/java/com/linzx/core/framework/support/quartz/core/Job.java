package com.linzx.core.framework.support.quartz.core;

import com.alibaba.fastjson.JSONObject;

public interface Job {

    /**
     * 任务名称
     */
    String getJobName();

    /**
     * 任务组别
     */
    String getJobGroup();

    /**
     * 任务表达式
     */
    String getCron();

    /**
     * 执行任务的类路径
     */
    String getClassPath();

    /**
     * 传递参数，JsonObject数据
     */
    JSONObject getParamJson();
}
