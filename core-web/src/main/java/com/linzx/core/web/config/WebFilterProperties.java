package com.linzx.core.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("project.web.filter")
@Configuration
public class WebFilterProperties {

    /**
     * 允许匿名访问的地址
     */
    private String[] anonUrls;

    /**
     * 登陆地址
     */
    private String loginUrl;

}
