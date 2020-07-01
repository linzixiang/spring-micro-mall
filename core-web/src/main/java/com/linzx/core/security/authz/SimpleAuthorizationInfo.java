package com.linzx.core.security.authz;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SimpleAuthorizationInfo implements AuthorizationInfo {

    protected Set<String> roles;

    protected Set<String> stringPermissions;

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

    @Override
    public Set<String> getRoles() {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        return roles;
    }

    @Override
    public Set<String> getStringPermissions() {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet<>();
        }
        return stringPermissions;
    }

    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void addRoles(Collection<String> roles) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(roles);
    }

    public void addStringPermission(String permission) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet<>();
        }
        this.stringPermissions.add(permission);
    }

    public void addStringPermissions(Collection<String> permissions) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet<>();
        }
        this.stringPermissions.addAll(permissions);
    }

}
