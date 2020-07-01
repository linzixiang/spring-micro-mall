package com.linzx.core.security.handle.impl;

import com.linzx.core.security.authz.AuthorizationInfo;
import com.linzx.core.security.authz.Logical;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.security.handle.AuthorizingAnnotationHandler;
import com.linzx.core.security.subject.DelegatingSubject;
import com.linzx.core.security.subject.Subject;

import java.lang.annotation.Annotation;

public class PermissionAnnotationHandler extends AuthorizingAnnotationHandler {

    public PermissionAnnotationHandler() {
        super(RequiresPermissions.class);
    }

    /**
     * 校验权限
     * @param anno
     */
    @Override
    public void assertAuthorized(Annotation anno) {
        // 不是所获取的RequiresPermissions注解不做处理
        if (!(anno instanceof RequiresPermissions))
            return;

        RequiresPermissions rpAnnotation = (RequiresPermissions) anno;
        // 获取权限值
        String[] perms = getAnnotationValue(rpAnnotation);
        Subject subject = DelegatingSubject.getInstance();
        AuthorizationInfo authorizationInfo = subject.getAuthorizationInfo();
        if (perms.length == 1) {
            subject.checkPermission(authorizationInfo, perms[0]);
            return;
        }

        if (Logical.AND.equals(rpAnnotation.logical())) {
            subject.checkPermissions(authorizationInfo, perms);
            return;
        }

        if (Logical.OR.equals(rpAnnotation.logical())) {
            boolean hasAtLeastOnePermission = false;
            for (String permission : perms)
                if (subject.isPermitted(authorizationInfo, permission))
                    hasAtLeastOnePermission = true;
            // 没有权限时验证第一个权限返回异常
            if (!hasAtLeastOnePermission)
                subject.checkPermission(authorizationInfo, perms[0]);
        }
    }

    /**
     * 获取权限值
     *
     * @param a
     * @return
     */
    public String[] getAnnotationValue(Annotation a) {
        RequiresPermissions requiresPermissions = (RequiresPermissions) a;
        return requiresPermissions.value();
    }

}
