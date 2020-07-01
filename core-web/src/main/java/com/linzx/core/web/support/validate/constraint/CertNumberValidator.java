package com.linzx.core.web.support.validate.constraint;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.linzx.core.web.support.validate.annotation.CertNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 身份证号码校验
 */
public class CertNumberValidator implements ConstraintValidator<CertNumber, Object> {

    /**
     * 证件类型
     */
    public enum CertType {
        /**
         * 身份证，支持18位、15位和港澳台的10位
         */
        ID_CARD
    }

    private CertType certType;

    @Override
    public void initialize(CertNumber constraintAnnotation) {
        certType = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String inputValue = Convert.toStr(value);
        if (StrUtil.isEmpty(inputValue)) {
            return false;
        }
        if (certType == CertType.ID_CARD && IdcardUtil.isValidCard(inputValue)) {
            return true;
        }
        return false;
    }

}
