package com.linzx.sso.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.linzx.common.util.PasswordUtils;
import com.linzx.core.common.constant.RedisKeyConstant;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.common.exception.DBDataException;
import com.linzx.core.framework.redis.RedisCacheService;
import com.linzx.sso.admin.domain.User;
import com.linzx.sso.admin.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户登陆
 */
@Service
public class LoginService implements ILoginService {

    @Autowired
    public RedisCacheService redisCacheService;

    @Autowired
    private UserService userService;

    /**
     * 验证码校验
     */
    public void checkCaptcha(String code, String uuid) {
        String verifyKey = RedisKeyConstant.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCacheService.getCacheObject(verifyKey);
        redisCacheService.deleteObject(verifyKey);
        if (StrUtil.isBlank(captcha)) {
            // 验证码已失效
            throw new BizException("user.jcaptcha.expire");
        }
        if (!StrUtil.equalsIgnoreCase(captcha, code)) {
            // 验证码不正确
            throw new BizException("user.jcaptcha.error");
        }
    }

    /**
     * 账号密码登陆
     */
    @Override
    public User loginByAccount(String account, String password) {
        User param = new User();
        param.setAccount(account);
        List<User> userList = userService.selectList(param).get();
        if (userList.size() == 0) {
            // 用户不存在
            throw new BizException("user.not.exist");
        }
        if (userList.size() > 1) {
            // 帐号不唯一
            throw new DBDataException("login.account.unique.error");
        }
        User user = userList.get(0);
        boolean isMatches = PasswordUtils.matches(user.getAccount(), user.getSalt(), user.getPassword(), password);
        if (!isMatches) {
            // 用户密码错误
            throw new BizException("login.password.error");
        }
        return user;
    }

}
