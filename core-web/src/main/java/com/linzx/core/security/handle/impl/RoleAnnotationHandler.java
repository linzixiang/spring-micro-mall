package com.linzx.core.security.handle.impl;

import com.linzx.core.security.authz.AuthorizationInfo;
import com.linzx.core.security.authz.Logical;
import com.linzx.core.security.authz.RequiresRoles;
import com.linzx.core.security.handle.AuthorizingAnnotationHandler;
import com.linzx.core.security.subject.DelegatingSubject;
import com.linzx.core.security.subject.Subject;

import java.lang.annotation.Annotation;

public class RoleAnnotationHandler extends AuthorizingAnnotationHandler {

    public RoleAnnotationHandler() {
        super(RequiresRoles.class);
    }

    /**
     * 获取权限值
     *
     * @param anno
     * @return
     */
    public String[] getAnnotationValue(Annotation anno) {
        RequiresRoles requiresRoles = (RequiresRoles) anno;
        return requiresRoles.value();
    }

    /**
     * 校验角色
     */
    @Override
    public void assertAuthorized(Annotation anno) {
        // 不是所获取的RequiresRoles注解不做处理
        if (!(anno instanceof RequiresRoles))
            return;
        RequiresRoles rrAnnotation = (RequiresRoles) anno;
        // 获取权限值
        String[] roles = getAnnotationValue(rrAnnotation);

        Subject subject = DelegatingSubject.getInstance();
        AuthorizationInfo authorizationInfo = subject.getAuthorizationInfo();
        // 校验单一角色
        if (roles.length == 1) {
            subject.checkRole(authorizationInfo, roles[0]);
            return;
        }
        // 校验多个角色
        if (Logical.AND.equals(rrAnnotation.logical())) {
            subject.checkRoles(authorizationInfo, roles);
            return;
        }
        if (Logical.OR.equals(rrAnnotation.logical())) {
            boolean hasAtLeastOneRole = false;
            for (String role : roles)
                if (subject.hasRole(authorizationInfo, role))
                    hasAtLeastOneRole = true;
            // 没有角色时验证第一个权限返回异常
            if (!hasAtLeastOneRole)
                subject.checkRole(authorizationInfo, roles[0]);
        }
    }
}
