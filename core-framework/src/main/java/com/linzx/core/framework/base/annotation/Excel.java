package com.linzx.core.framework.base.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface Excel {

    /**
     * 导出的文件名称
     */
    String fileName() default "";

    /**
     * 表名称，不指定默认就是文件名称
     */
    String sheetName() default "";

}
