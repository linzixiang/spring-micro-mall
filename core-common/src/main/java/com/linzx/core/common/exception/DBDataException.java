package com.linzx.core.common.exception;

import com.linzx.core.common.constant.ErrorCodeConstant;

/**
 * 数据异常
 */
public class DBDataException extends BaseException {

    public DBDataException(String msg) {
        this(msg, new Object[0]);
    }

    public DBDataException(String msg, Object[] args) {
        super(ErrorCodeConstant.DB_DATA_ERROR_CODE, msg, args);
    }
}
