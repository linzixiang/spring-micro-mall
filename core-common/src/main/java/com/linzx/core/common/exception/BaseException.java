package com.linzx.core.common.exception;

public class BaseException extends RuntimeException{

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     *
     */
    protected String msg;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public BaseException(String errorCode, String msg, Object[] args) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.args = args;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public Object[] getArgs() {
        return args;
    }
}
