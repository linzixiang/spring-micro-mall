package com.linzx.admin.monitor.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import com.linzx.core.framework.support.quartz.core.Job;
import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务 表 sys_schedule_job
 * 
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@Getter
@Setter
@Table("sys_schedule_job")
public class ScheduleJob extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
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

	@Override
	public void setId(Long id) {
		this.setJobId(id);
	}
	
	@Override
	public Long getId() {
		return this.getJobId();
	}
	
}
