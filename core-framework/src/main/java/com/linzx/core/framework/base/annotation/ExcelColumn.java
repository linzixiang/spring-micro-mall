package com.linzx.core.framework.base.annotation;

import cn.hutool.core.date.DatePattern;

import java.lang.annotation.*;

/**
 *  自定义导出Excel数据注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface ExcelColumn {

    /**
     * 列名称
     */
    String name();

    /**
     * 日期类型转换
     */
    String dateFormat() default DatePattern.NORM_DATETIME_PATTERN;

    /**
     * 排序，升序方式
     */
    int orderNum() default 0;

    /**
     * 使用的字典编码
     */
    String dictCode() default "";

    /**
     * 使用的字典编码
     */
    String tableCode() default "";

}
