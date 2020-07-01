package com.linzx.admin.system.dto.userInfo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 获取用户角色、权限
 */
@Setter
@Getter
@Builder
public class UserLoginInfo {

    /**
     * 角色编码
     */
    private Set<String> roles;

    /**
     * 权限编码
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private UserVo user;

}
