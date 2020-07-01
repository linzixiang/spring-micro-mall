package com.linzx.sso.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理系统登录响应报文
 */
@Setter
@Getter
public class AdminLoginRes {

    /** 会话令牌 **/
    private String token;

}
