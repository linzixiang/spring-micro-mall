package com.linzx.core.common.rpc;

import static com.linzx.core.common.constant.ErrorCodeConstant.*;

public class CommonRpcCode {

    public static class Common {

        public static IMessage SUCCESS = new Success();
        public static IMessage COMMON_ERROR = new CommonError();
        public static IMessage UNKNOW_ERROR_CODE = new UnknowError();
    }

    /**
     * 请求成功
     */
    public static class Success implements IMessage {

        @Override
        public String getCode() {
            return SUCCESS_CODE;
        }

        @Override
        public String getMessage() {
            return "请求成功";
        }
    }

    /**
     * 通用错误
     */
    public static class CommonError implements IMessage {

        @Override
        public String getCode() {
            return COMMON_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return "请求错误";
        }
    }

    /**
     * 未捕获异常
     */
    public static class UnknowError implements IMessage {

        @Override
        public String getCode() {
            return UNKNOW_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return "未捕获异常";
        }
    }

}
