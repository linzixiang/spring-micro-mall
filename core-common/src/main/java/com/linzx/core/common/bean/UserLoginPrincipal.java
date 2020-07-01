package com.linzx.core.common.bean;

import java.io.Serializable;

/**
 * 用户登录信息
 */
public class UserLoginPrincipal implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
