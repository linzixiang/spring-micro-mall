package com.linzx.core.web.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;
import com.linzx.core.common.exception.BaseException;

/**
 * 会话超时异常
 */
public class SessionTimeoutException extends BaseException {

    public SessionTimeoutException(String msg, Object[] args) {
        super(ErrorCodeConstant.LOGIN_TIMEOUT, msg, args);
    }
}
