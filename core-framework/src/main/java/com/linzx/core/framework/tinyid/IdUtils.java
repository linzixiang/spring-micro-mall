package com.linzx.core.framework.tinyid;

import cn.hutool.core.annotation.AnnotationUtil;
import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import com.xiaoju.uemc.tinyid.base.generator.IdGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("idGeneratorFactory")
public class IdUtils implements InitializingBean, ApplicationContextAware {

    private static IdGeneratorFactory idGeneratorFactory = null;

    private ApplicationContext applicationContext;

    /**
     * 获取数据库实体类的主键
     * @param entity
     * @return
     */
    public static final Long getDBSequenceNextId(BaseEntity entity) {
        Table tableAnnotation = AnnotationUtil.getAnnotation(entity.getClass(), Table.class);
        IdGenerator generator = idGeneratorFactory.getIdGenerator(tableAnnotation.value());
        return generator.nextId();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        idGeneratorFactory = (IdGeneratorFactory) applicationContext.getBean("idGeneratorFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
