package com.linzx;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.linzx.core.common.constant.WebSessionConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = WebSessionConstant.MAX_INACTIVE_INTERVAL_IN_SECONDS)
@NacosPropertySource(dataId = "example",autoRefreshed = true)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(SpringUtil.class)
public class WebSSOApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebSSOApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebSSOApplication.class);
    }

}
