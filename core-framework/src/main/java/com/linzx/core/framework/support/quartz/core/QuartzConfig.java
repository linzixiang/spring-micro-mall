package com.linzx.core.framework.support.quartz.core;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

public class QuartzConfig {

    @Component
    public class SchedulerFactoryCustomizer implements SchedulerFactoryBeanCustomizer {

        @Override
        public void customize(SchedulerFactoryBean schedulerFactoryBean) {

        }
    }

}
