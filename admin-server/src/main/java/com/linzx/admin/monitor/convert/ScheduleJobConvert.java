package com.linzx.admin.monitor.convert;

import com.linzx.admin.monitor.domain.ScheduleJob;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobAddReq;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobEditReq;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobListReq;
import com.linzx.admin.monitor.dto.scheduleJob.response.ScheduleJobEditRes;
import com.linzx.admin.monitor.dto.scheduleJob.response.ScheduleJobListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * ScheduleJob对象转换
 */
@Mapper(componentModel="spring")
public interface ScheduleJobConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    ScheduleJob scheduleJobListRes2ScheduleJob(ScheduleJobListReq scheduleJobListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    ScheduleJobListRes scheduleJob2ScheduleJobListRes(ScheduleJob scheduleJob);

    /**
     * 新增保存
     */
    @Mappings({})
    ScheduleJob scheduleJobAddReq2ScheduleJob(ScheduleJobAddReq scheduleJobAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    ScheduleJobEditRes scheduleJob2ScheduleJobEditRes(ScheduleJob scheduleJob);

    /**
     * 编辑保存
     */
    @Mappings({})
    ScheduleJob scheduleJobEditReq2ScheduleJob(ScheduleJobEditReq scheduleJobEditReq);
    
}
