package com.linzx.core.security;

import com.linzx.core.security.authz.AuthorizationInfo;

public interface AuthorizingRealm {

    AuthorizationInfo doGetAuthorizationInfo(Object principal);

}
