package com.linzx.admin.monitor.dto.scheduleJob.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务  新增保存参数
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@Getter
@Setter
public class ScheduleJobAddReq {

    /** 任务名称 */
    private String jobName;
    /** 任务组别 */
    private String jobGroup;
    /** 任务表达式 */
    private String cron;
    /** 类路径 */
    private String classPath;
    /** 任务参数 json格式 */
    private String paramJson;

}
