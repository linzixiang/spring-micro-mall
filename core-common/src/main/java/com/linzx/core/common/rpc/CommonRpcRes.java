package com.linzx.core.common.rpc;

import java.util.Date;

public class CommonRpcRes<T> extends BaseRpcRes {

    public static CommonRpcRes ok() {
        return ok(CommonRpcCode.Common.SUCCESS);
    }

    public static <T> CommonRpcRes<T> ok(IMessage msg) {
        return baseCreate(msg.getCode(), msg.getMessage(), true);
    }

    public static CommonRpcRes fail() {
        return fail(CommonRpcCode.Common.COMMON_ERROR);
    }

    public static CommonRpcRes fail(IMessage message) {
        return fail(message.getCode(), message.getMessage());
    }

    public static CommonRpcRes fail(String code, String msg) {
        return baseCreate(code, msg, false);
    }

    private static <T> CommonRpcRes<T> baseCreate(String code, String msg, boolean success) {
        CommonRpcRes result = new CommonRpcRes();
        result.setErrCode(code);
        result.setSuccess(success);
        result.setMessage(msg);
        result.setTimestamp(String.valueOf(new Date().getTime()));
        return result;
    }

    public CommonRpcRes<T> setResult(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return (T) super.getData();
    }

}
