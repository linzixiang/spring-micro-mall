package com.linzx;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboSearchApplication {

    public static void main(String[] args) throws IOException {
        initSentinel();
        SpringApplication.run(DubboSearchApplication.class, args);
        System.in.read();
    }

    /**
     * 初始化限流规则
     */
    public static void initSentinel() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("com.linzx.search.IDemoService:hello(com.linzx.search.dto.DemoReqDto)");// 限流的方法
        rule.setCount(10);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        /**
         * 限流的行为：1、直接拒绝；2、warm up;3、匀速排队
         */
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        /**
         * 针对来源的限流
         * 通过RpcContext.getContext().setAttachment("dubboApplication", "sentinelWeb");
         */
        // rule.setLimitApp("sentinelWeb");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
