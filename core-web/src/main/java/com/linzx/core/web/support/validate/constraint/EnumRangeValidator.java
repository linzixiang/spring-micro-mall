package com.linzx.core.web.support.validate.constraint;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.linzx.core.web.support.validate.annotation.EnumRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举校验
 */
public class EnumRangeValidator implements ConstraintValidator<EnumRange, Object> {

    private String[] allowValues;

    @Override
    public void initialize(EnumRange constraintAnnotation) {
        allowValues = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String inputValue = Convert.toStr(value);
        if (ArrayUtil.isEmpty(allowValues) || StrUtil.isEmpty(inputValue)) {
            return true;
        }
        return ArrayUtil.contains(allowValues, inputValue);
    }
}
