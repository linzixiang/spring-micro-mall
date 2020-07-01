package com.linzx.sso.admin.service.impl;

import com.linzx.core.framework.base.BaseService;
import com.linzx.sso.admin.domain.User;
import com.linzx.sso.admin.mapper.UserMapper;
import com.linzx.sso.admin.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现
 *
 * @author linzixiang
 * @date 2019_04_118
 */
@Service("userService")
public class UserService extends BaseService<Long, User, UserMapper> implements IUserService {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }
}
