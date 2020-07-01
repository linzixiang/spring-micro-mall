package com.linzx.core.common.rpc;

import java.io.Serializable;

public abstract class BaseRpcRes<T> implements Serializable {

    /**
     * 成功true，失败true
     */
    private boolean success = false;

    /**
     * 描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * success为false时，返回错误码
     */
    private String errCode;

    /**
     * 时间戳
     */
    private String timestamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
