package com.linzx.core.framework.support.xml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * api网关配置文件解析
 */
@Configuration
@Order(999)
public class ApiXmlResolverRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:config/gateway/*.xml");
        for(Resource resource : resources) {
//            Document rootElement = XmlUtil.readXML(resource.getInputStream());
//            Document rootElement = XmlUtil.readXML(resource.getFile());
//            NodeList nodeList = XmlUtil.getNodeListByXPath("/apiGateway/module/api", rootElement);
//            Node item = nodeList.item(0);
//            ApiBean apiBean = XmlUtil.xmlToBean(item, ApiBean.class);
//            String urlMap = apiBean.getUrlMap();
        }
    }

}
