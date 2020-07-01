package com.linzx.core.web.support.resolver;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.XmlUtil;
import com.linzx.core.framework.context.ContextManager;
import com.linzx.core.framework.support.xml.bean.DictBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * 字典xml解析
 */
@Order(998)
public class DictXmlResolverRunner implements CommandLineRunner {

    private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Override
    public void run(String... args) throws Exception {
        Resource[] resources = resolver.getResources("classpath*:config/dict/*.xml");
        for(Resource resource : resources) {
            Document document = XmlUtil.readXML(resource.getInputStream());
            Element rootElement = XmlUtil.getRootElement(document);
            List<Element> codeElementList = XmlUtil.getElements(rootElement, "option");
            for (Element element : codeElementList) {
                String keyName = element.getAttribute("keyName");
                String excludeStop = element.getAttribute("excludeStop");
                String excludeDelete = element.getAttribute("excludeDelete");
                DictBean dictBean = new DictBean();
                dictBean.setDictCode(element.getAttribute("code"));
                dictBean.setExcludeDelete(Convert.toBool(excludeStop, true));
                dictBean.setExcludeStop(Convert.toBool(excludeDelete, true));
                dictBean.setWhereExt(element.getAttribute("whereExt"));
                dictBean.setOrderBy(element.getAttribute("orderBy"));
                ContextManager.addDict(keyName, dictBean);
            }
        }
    }

}
