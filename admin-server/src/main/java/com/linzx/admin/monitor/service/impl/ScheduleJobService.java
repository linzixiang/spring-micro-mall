package com.linzx.admin.monitor.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.linzx.admin.monitor.domain.ScheduleJob;
import com.linzx.admin.monitor.mapper.ScheduleJobMapper;
import com.linzx.admin.monitor.service.IScheduleJobService;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.framework.base.BaseService;
import com.linzx.core.framework.support.quartz.core.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务  服务层实现
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@Service("scheduleJobService")
public class ScheduleJobService extends BaseService<Long, ScheduleJob, ScheduleJobMapper> implements IScheduleJobService {

    private ScheduleJobMapper scheduleJobMapper;

    public ScheduleJobService(ScheduleJobMapper scheduleJobMapper) {
        this.scheduleJobMapper = scheduleJobMapper;
    }

    @Override
    protected ScheduleJobMapper getMapper() {
        return this.scheduleJobMapper;
    }


    @Override
    public Integer insert(ScheduleJob entity) {
        checkGroupAndNameExist(entity);
        return super.insert(entity);
    }

    @Override
    public Integer update(ScheduleJob entity) {
        return super.update(entity);
    }

    @Override
    public List<Job> initJob() {
        ScheduleJob scheduleJobParam = new ScheduleJob();
        scheduleJobParam.setStatus(ScheduleJob.STATUS_NORMAL);
        List<ScheduleJob> scheduleJobs = scheduleJobMapper.selectList(scheduleJobParam);
        List<Job> jobList = new ArrayList<>();
        for (ScheduleJob scheduleJob : scheduleJobs) {
            jobList.add(new Job() {
                @Override
                public String getJobName() {
                    return scheduleJob.getJobName();
                }

                @Override
                public String getJobGroup() {
                    return scheduleJob.getJobGroup();
                }

                @Override
                public String getCron() {
                    return scheduleJob.getCron();
                }

                @Override
                public String getClassPath() {
                    return scheduleJob.getClassPath();
                }

                @Override
                public JSONObject getParamJson() {
                    return JSONObject.parseObject(scheduleJob.getParamJson());
                }
            });
        }
        return jobList;
    }

    /**
     * 如果触发器的名称和组别已存在，不允许重复插入
     */
    private void checkGroupAndNameExist(ScheduleJob entity) {
        if (StrUtil.isBlank(entity.getJobName()) || StrUtil.isBlank(entity.getJobGroup())) {
            return;
        }
        ScheduleJob scheduleJobParam = new ScheduleJob();
        scheduleJobParam.setJobGroup(entity.getJobGroup());
        scheduleJobParam.setJobName(entity.getJobName());
        scheduleJobParam.addParam("params.excludeJobId", entity.getJobId());
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectList(scheduleJobParam);
        if (scheduleJobList.size() > 0) {
            throw new BizException("schedule.job.repeat", new Object[] {scheduleJobParam.getJobName(), scheduleJobParam.getJobGroup()});
        }
    }

}
