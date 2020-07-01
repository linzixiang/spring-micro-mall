package com.linzx.core.common.constant;

/**
 * 响应错误码
 */
public class ErrorCodeConstant {

    /**
     * 请求成功
     */
    public static final String SUCCESS_CODE = "200";
    /**
     * 通用失败，客户端给出错误提示
     */
    public static final String COMMON_ERROR_CODE = "400";
    /**
     * 未认证（签名错误）
     */
    public static final String UNAUTHORIZED_ERROR_CODE = "401";
    /**
     * 接口未找到
     */
    public static final String NOT_FOUND_CODE = "404";
    /**
     * 服务器内部错误
     */
    public static final String INNER_SERVER_ERROR_CODE = "500";

    /**
     * 数据异常
     */
    public static final String DB_DATA_ERROR_CODE = "501";

    /**
     * 未知错误
     */
    public static final String UNKNOW_ERROR_CODE = "600";
    /**
     * 登陆超时
     */
    public static final String LOGIN_TIMEOUT = "2001";
    /**
     * 登陆超时
     */
    public static final String PERMISSION_DENY_CODE = "2002";

}
