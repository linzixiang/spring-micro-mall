package com.linzx.core.web.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;
import com.linzx.core.common.exception.BaseException;

public class NoHandlerFoundException extends BaseException {

    public NoHandlerFoundException(String msg, Object[] args) {
        super(ErrorCodeConstant.NOT_FOUND_CODE, msg, args);
    }

}
