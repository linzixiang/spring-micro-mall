package com.linzx.sso.admin.service;

import com.linzx.sso.admin.domain.User;

/**
 * 登录相关操作
 */
public interface ILoginService {

    /**
     * 验证码校验
     * @param code 验证码
     * @param uuid 唯一标识
     */
    void checkCaptcha(String code, String uuid);

    /**
     * 通过账号密码登陆
     * @param account 登陆账号
     * @param password 明文密码
     */
    User loginByAccount(String account, String password);



}
