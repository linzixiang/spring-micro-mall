package com.linzx.admin.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.domain.Role;
import com.linzx.admin.system.domain.User;
import com.linzx.admin.system.domain.UserRole;
import com.linzx.admin.system.mapper.UserMapper;
import com.linzx.admin.system.service.IMenuService;
import com.linzx.admin.system.service.IRoleService;
import com.linzx.admin.system.service.IUserService;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.framework.base.BaseService;
import com.linzx.core.security.AuthorizingRealm;
import com.linzx.core.security.authz.AuthorizationInfo;
import com.linzx.core.security.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务层实现
 * @author linzixiang
 * @date 2020-05-18 21:26:47
 */
@Service("userService")
public class UserService extends BaseService<Long, User, UserMapper> implements IUserService, AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    public UserService(UserMapper userMapper) {
        this.baseDAO = userMapper;
    }

    @Override
    protected UserMapper getMapper() {
        return this.baseDAO;
    }

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(Object principal) {
        UserLoginPrincipal userLoginPrincipal = (UserLoginPrincipal) principal;
        User user = this.getById(Convert.toLong(userLoginPrincipal.getUserId())).get();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (ObjectUtil.equal(user.getUserType(), User.USER_TYPE_1)) {
            // 超级管理员，具备所有权限
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("*:*:*");
            return authorizationInfo;
        }
        List<Role> roles = roleService.selectRoleByUserId(user.getUserId());
        List<Menu> menus = menuService.selectMenuByRole(roles);
        for (Menu menu : menus) {
            if (StrUtil.isBlank(menu.getPerms())) {
                continue;
            }
            authorizationInfo.addStringPermission(menu.getPerms());
        }
        for (Role role : roles) {
            if (StrUtil.isBlank(role.getRoleCode())) {
                continue;
            }
            authorizationInfo.addRole(role.getRoleCode());
        }
        return authorizationInfo;
    }

}
