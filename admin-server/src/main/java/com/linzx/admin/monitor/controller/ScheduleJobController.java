package com.linzx.admin.monitor.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.linzx.admin.monitor.convert.ScheduleJobConvert;
import com.linzx.admin.monitor.domain.ScheduleJob;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobAddReq;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobEditReq;
import com.linzx.admin.monitor.dto.scheduleJob.request.ScheduleJobListReq;
import com.linzx.admin.monitor.dto.scheduleJob.response.ScheduleJobListRes;
import com.linzx.admin.monitor.dto.scheduleJob.response.ScheduleJobEditRes;
import com.linzx.admin.monitor.service.IScheduleJobService;
import com.linzx.core.framework.support.quartz.util.ScheduleUtils;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定时任务 
 * @author linzixiang
 * @date 2020-07-11 18:22:58
 */
@RestController
@RequestMapping("/system/scheduleJob")
public class ScheduleJobController extends BaseController {

    @Autowired
    private IScheduleJobService scheduleJobService;
	
	@Autowired
    private ScheduleJobConvert scheduleJobConvert;

    /**
     * 定时任务  列表
     */
	@RequiresPermissions("system:scheduleJob:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(ScheduleJobListReq scheduleJobListReq) {
        startPage();
		List<ScheduleJob> scheduleJobList = selectScheduleJobList(scheduleJobListReq);
        Page page = (Page) scheduleJobList;
        List<ScheduleJobListRes> dataList = new ArrayList<>();
        for (ScheduleJob scheduleJob : scheduleJobList) {
			ScheduleJobListRes scheduleJobListRes = scheduleJobConvert.scheduleJob2ScheduleJobListRes(scheduleJob);
            dataList.add(scheduleJobListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }
	
	/**
     * 导出或列表 数据查询
     */
    private List<ScheduleJob> selectScheduleJobList(ScheduleJobListReq scheduleJobListReq) {
        ScheduleJob scheduleJobParam = scheduleJobConvert.scheduleJobListRes2ScheduleJob(scheduleJobListReq);
        return scheduleJobService.selectList(scheduleJobParam).get();
    }

    /**
     * 定时任务  新增查询
     */
	@RequiresPermissions("system:scheduleJob:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 定时任务  新增保存
     */
	@RequiresPermissions("system:scheduleJob:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(@Validated ScheduleJobAddReq scheduleJobAddReq) {
		ScheduleJob scheduleJob = scheduleJobConvert.scheduleJobAddReq2ScheduleJob(scheduleJobAddReq);
        scheduleJob.setStatus(ScheduleJob.STATUS_STOP);
        scheduleJobService.insert(scheduleJob);
        return CommonAjaxResult.ok().setResult(scheduleJob.getId());
    }

    /**
     * 定时任务  修改查询
     */
	@RequiresPermissions("system:scheduleJob:edit")
    @GetMapping("/preEdit/{scheduleJobId}")
    public CommonAjaxResult preEdit(@PathVariable("scheduleJobId") Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobService.getById(scheduleJobId).get();
		ScheduleJobEditRes scheduleJobEditRes = scheduleJobConvert.scheduleJob2ScheduleJobEditRes(scheduleJob);
        return CommonAjaxResult.ok().setResult(scheduleJobEditRes);
    }

	/**
     * 定时任务  修改保存
     */
	@RequiresPermissions("system:scheduleJob:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(@Validated ScheduleJobEditReq scheduleJobEditReq) {
        ScheduleJob scheduleJobOld = scheduleJobService.getById(scheduleJobEditReq.getJobId()).get();
        if (!scheduleJobOld.getStatus().equals(ScheduleJob.STATUS_NORMAL)) {
            return CommonAjaxResult.fail("edit.schedule.job.running");
        }
        ScheduleJob scheduleJob = scheduleJobConvert.scheduleJobEditReq2ScheduleJob(scheduleJobEditReq);
        scheduleJobService.update(scheduleJob);
        return CommonAjaxResult.ok();
    }

    /**
     * 定时任务  删除，例如scheduleJobId=1&scheduleJobId=2
     */
	@RequiresPermissions("system:scheduleJob:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] scheduleJobId) {
        List<ScheduleJob> scheduleJobs = scheduleJobService.selectByIds(scheduleJobId).get();
        for (ScheduleJob scheduleJob : scheduleJobs) {
            if (!scheduleJob.getStatus().equals(ScheduleJob.STATUS_NORMAL)) {
                return CommonAjaxResult.fail("del.schedule.job.running", scheduleJob.getJobName(), scheduleJob.getJobName());
            }
        }
        scheduleJobService.deleteByIds(scheduleJobId);
        return CommonAjaxResult.ok();
    }

    /**
     * 开启/停用 定时任务
     * @param status
     * @return
     */
    @RequiresPermissions("system:scheduleJob:change")
    @PostMapping("/changeStatus")
    public CommonAjaxResult changeStatus(
            @RequestParam("jobId") Long jobId,
            @RequestParam("status") Integer status) throws Exception {
        ScheduleJob scheduleJob = scheduleJobService.getById(jobId).get();
        if (ScheduleJob.STATUS_NORMAL.equals(status)) {
            ScheduleUtils.resumeJob(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        } else if (ScheduleJob.STATUS_STOP.equals(status)) {
            ScheduleUtils.pauseJob(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        } else {
            return CommonAjaxResult.fail("request.param.error", "status");
        }
        ScheduleJob params = new ScheduleJob();
        params.setJobId(jobId);
        params.setStatus(status);
        scheduleJobService.update(params);
	    return CommonAjaxResult.ok();
    }
	
	/**
     * 定时任务  导出
     */
	@RequiresPermissions("system:scheduleJob:export")
	@RequestMapping("/export")
    public void export(ScheduleJobListReq scheduleJobListReq) throws Exception {
        // 数据集准备
        List<ScheduleJob> scheduleJobList = selectScheduleJobList(scheduleJobListReq);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            ScheduleJobListRes scheduleJobListRes = scheduleJobConvert.scheduleJob2ScheduleJobListRes(scheduleJob);
            dataList.add(BeanUtil.beanToMap(scheduleJobListRes));
        }
        exportExcel(dataList, ScheduleJobListRes.class);
    }
	
}
