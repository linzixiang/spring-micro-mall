package com.linzx.core.web.support.validate.annotation;

import com.linzx.core.web.support.validate.constraint.CertNumberValidator;
import com.linzx.core.web.support.validate.constraint.CertNumberValidator.CertType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CertNumberValidator.class)
public @interface CertNumber {

    String message() default "证件号码不合法";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    CertType value() default CertType.ID_CARD;

}
