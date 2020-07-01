package com.linzx.core.framework.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {

    /** mybatis乐观锁 **/
    private VersionLocker versionLocker = new VersionLocker();

    /** 分页器 **/
    private PageHelper pageHelper = new PageHelper();

    @Getter
    @Setter
    public static class VersionLocker {

        /** 乐观锁数据库的列名 **/
        private String column = "revision";

        /** 乐观锁java字段名 **/
        private String field = "revision";
    }

    @Getter
    @Setter
    public static class PageHelper {

        private String helperDialect = "mysql";

        private boolean reasonable = false;

        private boolean supportMethodsArguments = false;

        private String params = "count=countSql";

        private String pageSizeZero = "count=countSql";

    }

}
