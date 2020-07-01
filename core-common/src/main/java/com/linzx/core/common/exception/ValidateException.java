package com.linzx.core.common.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;

/**
 * 参数校验异常
 */
public class ValidateException extends BaseException {

    public ValidateException(String message, Object[] args) {
        super(ErrorCodeConstant.COMMON_ERROR_CODE, message, args);
    }
}
