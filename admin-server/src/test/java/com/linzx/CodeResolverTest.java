package com.linzx;

import cn.hutool.core.util.XmlUtil;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.List;

public class CodeResolverTest {

    @Test
    public void initCode() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources("classpath*:config/code/*.xml");
            for(Resource resource : resources) {
                Document document = XmlUtil.readXML(resource.getInputStream());
                Element rootElement = XmlUtil.getRootElement(document);
                List<Element> codeElementList = XmlUtil.getElements(rootElement, "code");
                for (Element element : codeElementList) {
                    String keyName = element.getAttribute("keyName");
                    String tableName = element.getAttribute("tableName");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
