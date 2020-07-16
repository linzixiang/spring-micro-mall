package com.linzx.core.framework.support.quartz.core;

import java.util.List;

public interface QuartzJobInit {

    /**
     * 查询需要初始化的定时任务
     * @return
     */
     List<Job> initJob();

}
