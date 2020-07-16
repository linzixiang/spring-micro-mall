package com.linzx.core.framework.support.quartz;


import com.linzx.core.framework.support.quartz.core.MyJobFactory;
import com.linzx.core.framework.support.quartz.core.QuartzScheduleRunner;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启定时器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({QuartzScheduleRunner.class, MyJobFactory.class})
public @interface EnableQuartz {
}
