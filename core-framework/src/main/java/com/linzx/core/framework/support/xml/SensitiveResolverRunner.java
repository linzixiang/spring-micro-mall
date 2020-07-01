package com.linzx.core.framework.support.xml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 敏感字段xml解析
 */
@Configuration
@Order(997)
public class SensitiveResolverRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

}
