package com.linzx.admin.monitor.service;

import com.linzx.admin.monitor.domain.ScheduleJob;
import com.linzx.core.framework.support.quartz.core.QuartzJobInit;

import java.util.List;
import java.util.Optional;

/**
 * 定时任务  服务层接口
 * @author linzixiang
 * @date  2020-07-11 18:22:58
 */
public interface IScheduleJobService extends QuartzJobInit {

	Optional<ScheduleJob> getById(Long id);

    Optional<List<ScheduleJob>> selectByIds(Long[] ids);

    Optional<List<ScheduleJob>> selectList(ScheduleJob scheduleJob);

    Integer insert(ScheduleJob scheduleJob);

    Integer insertBatch(List<ScheduleJob> scheduleJobList);

    Integer update(ScheduleJob scheduleJob);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);
	
}
