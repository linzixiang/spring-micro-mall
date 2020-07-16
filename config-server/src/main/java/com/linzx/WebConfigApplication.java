package com.linzx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.client.RestTemplate;

/**
 * 配置中心
 */
@EnableConfigServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebConfigApplication {
    @Autowired
    private RestTemplate restTemplate;

    public void main( String[] args ) {
        SpringApplication.run(WebConfigApplication.class, args);
//        restTemplate.getForObject("http://spring-cloud-order-service/orders", String.class);
        restTemplate.getForObject("http://spring-cloud-order-service/xxx", String.class);
    }




@Autowired
private LoadBalancerClient loadBalancerClient;

@LoadBalanced
public RestTemplate restTemplate(RestTemplateBuilder builder) {
    ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-order-service");
    String url = String.format("http://%s:%s/xxx", serviceInstance.getHost(), serviceInstance.getPort());
    restTemplate.getForObject(url, String.class);
    return builder.build();
}

}
