package com.linzx.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@DubboComponentScan("com.linzx.search")
public class DubboConfig {

    @Bean
     public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("search-server");
        applicationConfig.setOwner("linzx");
        return applicationConfig;
     }

     @Bean
     public RegistryConfig registryConfig() {
         RegistryConfig registryConfig = new RegistryConfig();
         registryConfig.setAddress("zookeeper://139.196.72.210:2181");
         return registryConfig;
     }

     @Bean
     public ProtocolConfig protocolConfig() {
         ProtocolConfig protocolConfig = new ProtocolConfig();
         protocolConfig.setName("dubbo");
         protocolConfig.setPort(2181);
         return  protocolConfig;
     }

}
