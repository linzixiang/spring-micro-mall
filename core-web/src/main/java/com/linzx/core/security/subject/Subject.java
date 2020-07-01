package com.linzx.core.security.subject;

import com.linzx.core.security.authz.AuthorizationInfo;

import java.util.Set;

/**
 * 权限校验接口
 */
public interface Subject {

    /**
     * 校验单一权限
     *
     * @param permission
     *            权限值
     */
    void checkPermission(AuthorizationInfo authorizationInfo, String permission);

    /**
     * 校验多个权限，必须全部通过
     *
     * @param permissions
     *            权限值
     */
    void checkPermissions(AuthorizationInfo authorizationInfo, String... permissions);

    /**
     * 校验权限
     *
     * @param permission
     *            权限值
     * @return
     */
    boolean isPermitted(AuthorizationInfo authorizationInfo, String permission);

    /**
     * 获取用户权限
     * @return
     */
    Set<String> getPermissions(AuthorizationInfo authorizationInfo);

    /**
     * 校验单一角色
     *
     * @param role
     *            角色值
     */
    void checkRole(AuthorizationInfo authorizationInfo, String role);

    /**
     * 校验多个角色
     *
     * @param roles
     *            角色值
     */
    void checkRoles(AuthorizationInfo authorizationInfo, String... roles);

    /**
     * 校验角色
     *
     * @param role
     *            角色值
     * @return
     */
    boolean hasRole(AuthorizationInfo authorizationInfo, String role);

    /**
     * 获取用户角色
     * @return
     */
    Set<String> getRoles(AuthorizationInfo authorizationInfo);

    /**
     * 获取当前登陆用户的角色和权限数据
     * @return
     */
    AuthorizationInfo getAuthorizationInfo();

}
