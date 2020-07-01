package com.linzx.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.Executor;

public class NacosSdkDemo {

    /**
     * 获取配置
     */
    @Test
    public void getConfig() {
        String serverAddr = "139.196.72.210:8848";
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            String context = configService.getConfig(dataId, groupId, 3000);
            System.out.println(context);
            // 监听数据的变化
            configService.addListener(dataId, groupId, new Listener() {

                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                   System.out.println(configInfo);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

}
