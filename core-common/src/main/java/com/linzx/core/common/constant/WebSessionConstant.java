package com.linzx.core.common.constant;

/**
 * session属性常量
 */
public class WebSessionConstant {

    /**
     * header存放的token
     */
    public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";

    /**
     * 是否登录，true表示已登录
     */
    public static final String IS_LOGIN = "IS_LOGIN";

    /**
     * 登陆的用户的信息
     */
    public static final String USER_LOGIN_PRINCIPAL = "USER_LOGIN_PRINCIPAL";

    /**
     * 用户的权限信息
     */
    public static final String USER_AUTHORIZATION_INFO = "USER_AUTHORIZATION_INFO";

    /**
     * 超时时间
     */
    public static final int MAX_INACTIVE_INTERVAL_IN_SECONDS = 60 * 60 * 8;

}
