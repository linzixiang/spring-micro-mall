package com.linzx.core.web.support.resolver;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.linzx.core.framework.context.ContextManager;
import com.linzx.core.framework.support.xml.bean.CodeBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * 启动解析Code.xml配置
 */
@Order(1000)
public class CodeXmlResolverRunner implements CommandLineRunner {

    private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Override
    public void run(String... args) throws Exception {
        Resource[] resources = resolver.getResources("classpath*:config/code/*.xml");
        for(Resource resource : resources) {
            Document document = XmlUtil.readXML(resource.getInputStream());
            Element rootElement = XmlUtil.getRootElement(document);
            List<Element> codeElementList = XmlUtil.getElements(rootElement, "code");
            for (Element element : codeElementList) {
                String keyName = StrUtil.trim(element.getAttribute("keyName"));
                CodeBean codeBean = new CodeBean();
                codeBean.setTableName(StrUtil.trim(element.getAttribute("tableName")));
                codeBean.setNameField(StrUtil.trim(element.getAttribute("nameField")));
                codeBean.setValueField(StrUtil.trim(element.getAttribute("valueField")));
                codeBean.setWhereExt(StrUtil.trim(element.getAttribute("whereExt")));
                codeBean.setOrderBy(StrUtil.trim(element.getAttribute("orderBy")));
                ContextManager.addCode(keyName, codeBean);
            }
        }
    }

    public static void main(String[] args) {

    }
    
}
