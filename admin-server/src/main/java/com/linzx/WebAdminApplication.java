package com.linzx;


import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.web.support.resolver.CodeXmlResolverRunner;
import com.linzx.core.web.support.resolver.DictXmlResolverRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.ArrayList;
import java.util.List;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = WebSessionConstant.MAX_INACTIVE_INTERVAL_IN_SECONDS)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({SpringUtil.class, CodeXmlResolverRunner.class, DictXmlResolverRunner.class})
public class WebAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        initSentinel();
        SpringApplication.run(WebAdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebAdminApplication.class);
    }

    public static void initSentinel() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setCount(10);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
