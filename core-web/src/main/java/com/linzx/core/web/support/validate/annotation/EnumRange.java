package com.linzx.core.web.support.validate.annotation;

import com.linzx.core.web.support.validate.constraint.EnumRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumRangeValidator.class)
public @interface EnumRange {

    String message() default "参数不合法";

    /**
     * 允许传参的范围枚举
     */
    String[] value() default { };

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
