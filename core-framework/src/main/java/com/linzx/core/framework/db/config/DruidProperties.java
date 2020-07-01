package com.linzx.core.framework.db.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

    public Integer initialSize = 5;// 初始连接数

    public int minIdle = 10; // 最小连接池数量

    public int maxActive = 20;// 最大连接池数量

    public int maxWait = 60000; // 获取连接等待超时的时间

    public int timeBetweenEvictionRunsMillis = 60000;// 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒

    public int minEvictableIdleTimeMillis = 300000; // 一个连接在池中最小生存的时间，单位是毫秒

    public int  maxEvictableIdleTimeMillis = 900000;// 一个连接在池中最大生存的时间，单位是毫秒

    public String  validationQuery = "SELECT 1 FROM DUAL";// 检测连接是否有效sql

    public boolean testWhileIdle = true;

    public boolean testOnBorrow = false;

    public boolean testOnReturn = false;

}
