package com.linzx.core.framework.support.quartz.entity;

import com.linzx.core.framework.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Job extends BaseEntity<Long> implements Serializable {

    /**
     * 任务Id
     */
    private Long jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组别
     */
    private String jobGroup;
    /**
     * 任务表达式
     */
    private String cron;
    /**
     * 执行任务的类路径
     */
    private String classPath;
    /**
     * 传递map参数，JsonObject数据
     */
    private String paramJson;

    @Override
    public void setId(Long id) {
        this.jobId = id;
    }

    @Override
    public Long getId() {
        return this.jobId;
    }
}
