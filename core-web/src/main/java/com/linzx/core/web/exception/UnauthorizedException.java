package com.linzx.core.web.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;
import com.linzx.core.common.exception.BaseException;

/**
 * 未认证异常
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        this("permission.deny", new Object[0]);
    }

    public UnauthorizedException(String msg, Object[] args) {
        super(ErrorCodeConstant.UNAUTHORIZED_ERROR_CODE, msg, args);
    }
}
