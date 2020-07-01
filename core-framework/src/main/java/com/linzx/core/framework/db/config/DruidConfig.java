package com.linzx.core.framework.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.linzx.core.framework.db.datasource.DataSourceType;
import com.linzx.core.framework.db.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@DependsOn({"druidProperties"})
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return initDruidDataSource(dataSource, druidProperties);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return initDruidDataSource(dataSource, druidProperties);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.toString(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.toString(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    public DruidDataSource initDruidDataSource(DruidDataSource dataSource, DruidProperties druidProperties) {
        /** 配置初始化大小、最小、最大 */
        dataSource.setInitialSize(druidProperties.getInitialSize());
        dataSource.setMaxActive(druidProperties.getMaxActive());
        dataSource.setMinIdle(druidProperties.getMinIdle());

        /** 配置获取连接等待超时的时间 */
        dataSource.setMaxWait(druidProperties.getMaxWait());

        /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());

        /** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        dataSource.setMaxEvictableIdleTimeMillis(druidProperties.getMaxEvictableIdleTimeMillis());

        /**
         * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
         */
        dataSource.setValidationQuery(druidProperties.getValidationQuery());
        /** 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 */
        dataSource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        /** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        dataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        /** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        dataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        return dataSource;
    }
}
