package com.linzx.core.security.subject;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.security.AuthorizingRealm;
import com.linzx.core.security.authz.AuthorizationInfo;
import com.linzx.core.web.exception.UnauthorizedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Set;

public class DelegatingSubject implements Subject {

    private DelegatingSubject() {}

    private static class SingleTonHolder{
        private static Subject INSTANCE = new DelegatingSubject();
    }

    public static Subject getInstance(){
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void checkPermission(AuthorizationInfo authorizationInfo, String permission) {
        if (!isPermitted(authorizationInfo, permission)) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public void checkPermissions(AuthorizationInfo authorizationInfo, String... permissions) {
        for(String permission : permissions) {
            checkPermission(authorizationInfo, permission);
        }
    }

    @Override
    public boolean isPermitted(AuthorizationInfo authorizationInfo, String permission) {
        if (StrUtil.isBlank(permission)) {
            return false;
        }
        String[] permissionSplit = StrUtil.split(permission, ":");
        Set<String> permissionsOwner = authorizationInfo.getStringPermissions();
        Iterator<String> iterator = permissionsOwner.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (StrUtil.equals(next, permission)) {
                return true;
            }
            String[] permissionNextSplit = StrUtil.split(next, ":");
            if (permissionNextSplit.length != permissionSplit.length) {
                // 权限长度必须一致
                return false;
            }
            for(int i = 0; i < permissionSplit.length; i++) {
                if (StrUtil.equals("*", permissionNextSplit[i])) {
                    // * 表示不检查权限
                    continue;
                }
                if (!StrUtil.equals(permissionNextSplit[i], permissionSplit[i])) {
                     return false;
                }
            }

        }
        return true;
    }

    @Override
    public Set<String> getPermissions(AuthorizationInfo authorizationInfo) {
        return authorizationInfo.getStringPermissions();
    }

    @Override
    public void checkRole(AuthorizationInfo authorizationInfo, String role) {
        if (!hasRole(authorizationInfo, role)) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public void checkRoles(AuthorizationInfo authorizationInfo, String... roles) {
        for (String role : roles) {
            checkRole(authorizationInfo, role);
        }
    }

    @Override
    public boolean hasRole(AuthorizationInfo authorizationInfo, String role) {
        Set<String> rolesOwner = authorizationInfo.getRoles();
        Iterator<String> iterator = rolesOwner.iterator();
        while (iterator.hasNext()) {
            String roleOwner = iterator.next();
            if (StrUtil.equals(roleOwner, role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> getRoles(AuthorizationInfo authorizationInfo) {
        return authorizationInfo.getRoles();
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        AuthorizationInfo authorizationInfo = (AuthorizationInfo) session.getAttribute(WebSessionConstant.USER_AUTHORIZATION_INFO);
        if (authorizationInfo == null) {
            synchronized (session.getId()) {
                AuthorizingRealm realm = SpringUtil.getBean(AuthorizingRealm.class);
                if (realm != null) {
                    Object principal = session.getAttribute(WebSessionConstant.USER_LOGIN_PRINCIPAL);
                    authorizationInfo = realm.doGetAuthorizationInfo(principal);
                    session.setAttribute(WebSessionConstant.USER_AUTHORIZATION_INFO, authorizationInfo);
                }
            }
        }
        return authorizationInfo;
    }

    public static void main(String[] args) {
        String[] permissionNextSplit = StrUtil.split("*:*:*", ":");
        permissionNextSplit = "*:*:*".split(":");
        System.out.println(permissionNextSplit);
    }

}
