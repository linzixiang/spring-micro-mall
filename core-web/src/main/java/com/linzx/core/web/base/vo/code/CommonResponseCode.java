package com.linzx.core.web.base.vo.code;


import static com.linzx.core.common.constant.ErrorCodeConstant.*;

public class CommonResponseCode {

    public static class Common {

        public static IMessage SUCCESS = new Success(); // 请求成功：200
        public static IMessage COMMON_ERROR = new CommonError("error.unknow"); // 失败，客户端直接提示：400
        public static IMessage UNAUTHORIZED = new Unauthorized(); //  未认证：401
        public static IMessage NOT_FOUND = new NotFound(); //  接口不存在:404
        public static IMessage INNER_SERVER_ERROR = new InnerServerError();//服务器内部错误：500
        public static IMessage UNKNOW_ERROR = new UnknowError(); // 未知错误：501

        public static LoginTimeout LOGIN_TIMEOUT = new LoginTimeout(); // 会话登陆超时
        public static PermissionDeny PERMISSION_DENY = new PermissionDeny(); // 权限不够
    }

    /**
     * 请求成功
     */
    public final static class Success implements IMessage {

        @Override
        public String getCode() {
            return SUCCESS_CODE;
        }

        @Override
        public String getMessage() {
            return "request.success";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 通用错误
     */
    public final static class CommonError implements IMessage {

        private String message;

        private Object[] messageArgs;

        public CommonError(String message) {
            this.message = message;
        }

        public CommonError(String message, Object[] messageArgs) {
            this.message = message;
            this.messageArgs = messageArgs;
        }

        @Override
        public String getCode() {
            return COMMON_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public Object[] getMessageArgs() {
            return messageArgs;
        }
    }

    /**
     * 未认证（签名错误）
     */
    public final static class Unauthorized implements IMessage {

        @Override
        public String getCode() {
            return UNAUTHORIZED_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return "error.unauthorized";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 接口不存在
     */
    public static class NotFound implements IMessage {

        @Override
        public String getCode() {
            return NOT_FOUND_CODE;
        }

        @Override
        public String getMessage() {
            return "error.api.not.found";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 服务器内部错误
     */
    public static class InnerServerError implements IMessage {

        @Override
        public String getCode() {
            return INNER_SERVER_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return "error.inneral.server.exception";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 服务器内部错误
     */
    public static class UnknowError implements IMessage {

        @Override
        public String getCode() {
            return UNKNOW_ERROR_CODE;
        }

        @Override
        public String getMessage() {
            return "error.cannot.catch";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 会话登陆超时
     */
    public static class LoginTimeout implements IMessage {

        @Override
        public String getCode() {
            return LOGIN_TIMEOUT;
        }

        @Override
        public String getMessage() {
            return "login.timeout";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

    /**
     * 权限不足
     */
    public static class PermissionDeny implements IMessage {

        @Override
        public String getCode() {
            return PERMISSION_DENY_CODE;
        }

        @Override
        public String getMessage() {
            return "permission.deny";
        }

        @Override
        public Object[] getMessageArgs() {
            return new Object[0];
        }
    }

}
