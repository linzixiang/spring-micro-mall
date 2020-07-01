package com.linzx.core.web.support.validate.constraint;

import cn.hutool.core.util.StrUtil;
import com.linzx.core.web.support.validate.annotation.MobilePhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号校验
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhoneNumber, String> {

    private Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        return pattern.matcher(value).matches();
    }

}
