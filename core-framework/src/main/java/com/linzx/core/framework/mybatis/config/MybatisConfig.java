package com.linzx.core.framework.mybatis.config;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * mybatis在原先自动配置的基础上增加配置
 */
@Configuration
@AutoConfigureAfter({MybatisAutoConfiguration.class})
@MapperScan({"com.linzx.*.*.mapper"})
public class MybatisConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private MybatisProperties mybatisProperties;

    /**
     * 乐观锁,分页拦截器
     */
    @PostConstruct
    public void addOptimisticLockerInterceptor() {
//        OptimisticLocker optimisticLocker = new OptimisticLocker();
//        Properties optimisticLockerProperties = new Properties();
//        optimisticLockerProperties.put("versionColumn", mybatisProperties.getVersionLocker().getColumn());
//        optimisticLockerProperties.put("versionField", mybatisProperties.getVersionLocker().getField());
//        optimisticLocker.setProperties(optimisticLockerProperties);
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties pageProperties = new Properties();
        pageProperties.put("supportMethodsArguments", Convert.toStr(mybatisProperties.getPageHelper().isSupportMethodsArguments()));
        pageProperties.put("support-methods-arguments", Convert.toStr(mybatisProperties.getPageHelper().isSupportMethodsArguments()));
        pageProperties.put("helperDialect", mybatisProperties.getPageHelper().getHelperDialect());
        pageProperties.put("helper-dialect", mybatisProperties.getPageHelper().getHelperDialect());
        pageProperties.put("pageSizeZero", mybatisProperties.getPageHelper().getPageSizeZero());
        pageProperties.put("page-size-zero", mybatisProperties.getPageHelper().getPageSizeZero());
        pageProperties.put("reasonable", Convert.toStr(mybatisProperties.getPageHelper().isReasonable()));
        pageProperties.put("params", mybatisProperties.getPageHelper().getParams());
        pageInterceptor.setProperties(pageProperties);
        Iterator<SqlSessionFactory> iterator = this.sqlSessionFactoryList.iterator();
        while(iterator.hasNext()) {
            SqlSessionFactory sqlSessionFactory = iterator.next();
            sqlSessionFactory.getConfiguration().addInterceptor(pageInterceptor);
//            sqlSessionFactory.getConfiguration().addInterceptor(optimisticLocker);
        }
    }

}
