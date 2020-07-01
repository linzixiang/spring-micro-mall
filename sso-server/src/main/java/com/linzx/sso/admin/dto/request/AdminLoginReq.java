package com.linzx.sso.admin.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理用户登录请求报文
 */
@Setter
@Getter
public class AdminLoginReq {

    /**
     * 登录帐号/用户名
     * @required
     */                      
    private String userName;

    /**
     * 登陆密码
     * @required
     */
    private String password;

    /**
     * 验证码
     * @required
     */
    private String code;

    /**
     * 唯一标识
     * @required
     */
    private String uuid;

}
