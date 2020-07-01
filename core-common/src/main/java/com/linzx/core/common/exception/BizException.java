package com.linzx.core.common.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;

/**
 * 业务层异常类
 */
public class BizException extends BaseException {

    public BizException(String message){
        this(message, new Object[0]);
    }

    public BizException(String message, Object[] args) {
        super(ErrorCodeConstant.COMMON_ERROR_CODE, message, args);
    }
}
