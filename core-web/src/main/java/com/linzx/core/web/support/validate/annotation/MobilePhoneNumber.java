package com.linzx.core.web.support.validate.annotation;

import com.linzx.core.web.support.validate.constraint.MobilePhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobilePhoneValidator.class)
public @interface MobilePhoneNumber {

    String message() default "手机号格式有误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
