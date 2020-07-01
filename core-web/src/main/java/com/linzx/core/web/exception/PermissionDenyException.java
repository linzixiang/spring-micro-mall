package com.linzx.core.web.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;
import com.linzx.core.common.exception.BaseException;

public class PermissionDenyException extends BaseException {

    public PermissionDenyException(String msg, Object[] args) {
        super(ErrorCodeConstant.PERMISSION_DENY_CODE, msg, args);
    }

}
