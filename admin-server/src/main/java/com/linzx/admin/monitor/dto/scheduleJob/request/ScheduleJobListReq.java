package com.linzx.admin.monitor.dto.scheduleJob.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务  列表查询参数
 * 
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@Getter
@Setter
public class ScheduleJobListReq {

    /** 状态 （1正常 2停用） */
    private Integer status;

}
