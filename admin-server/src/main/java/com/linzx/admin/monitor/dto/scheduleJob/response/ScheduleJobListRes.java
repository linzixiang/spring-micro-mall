package com.linzx.admin.monitor.dto.scheduleJob.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务  列表查询响应
 * 
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@Getter
@Setter
public class ScheduleJobListRes {

    /** 主键 */
    private Long jobId;
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
    /** 备注 */
    private String remark;
    /** 状态 （1正常 2停用） */
    private Integer status;

}
