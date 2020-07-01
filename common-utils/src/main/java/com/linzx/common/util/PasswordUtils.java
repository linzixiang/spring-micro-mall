package com.linzx.common.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 密码工具
 */
public final class PasswordUtils {

    /**
     * 检查密码是否正确
     * @param loginAccount 登录帐号
     * @param salt 加密随机数
     * @param dbPasswod 数据库中的密码
     * @param inputPassword 用户输入的密码
     * @return
     */
    public static boolean matches(String loginAccount, String salt, String dbPasswod, String inputPassword) {
        String encryptPassword = encryptPassword(loginAccount, inputPassword, salt);
        return StrUtil.equals(dbPasswod, encryptPassword);
    }

    /**
     * 密码MD5加密后返回
     * @param username 用户名
     * @param password 密码
     * @param salt 随机数
     * @return
     */
    public static String encryptPassword(String username, String password, String salt) {
        return SecureUtil.md5(username + password + salt);
    }

    /**
     * 生成随机数，用于加密
     */
    public static String randomSalt() {
        return RandomUtil.randomString(6);
    }

    public static void main(String[] args) {
        String encryptPassword = encryptPassword("admin", "123456", "79c98f");
        System.out.println(encryptPassword);
        boolean matches = matches("admin", "79c98f", "a0331f777dc9caece89cf3e946e0f038", "123457");
        System.out.println(matches);
    }

}
