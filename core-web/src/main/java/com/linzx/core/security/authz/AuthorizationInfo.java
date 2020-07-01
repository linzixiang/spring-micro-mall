package com.linzx.core.security.authz;

import java.io.Serializable;
import java.util.Set;

public interface AuthorizationInfo extends Serializable {

    /**
     * 角色
     * @return
     */
    Set<String> getRoles();

    /**
     * 权限编码
     * @return
     */
    Set<String> getStringPermissions();

}
