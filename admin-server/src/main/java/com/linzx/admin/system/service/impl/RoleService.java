package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.Role;
import com.linzx.admin.system.domain.UserRole;
import com.linzx.admin.system.mapper.RoleMapper;
import com.linzx.admin.system.service.IRoleService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务层实现
 * @author linzixiang
 * @date 2020-06-10 16:33:11
 */
@Service("roleService")
public class RoleService extends BaseService<Long, Role, RoleMapper> implements IRoleService {

    private RoleMapper roleMapper;

    @Autowired
    private UserRoleService userRoleService;

    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
    
    @Override
    protected RoleMapper getMapper() {
        return this.roleMapper;
    }

    /**
     * 查找用户的角色
     * @param userId
     * @return
     */
    public List<Role> selectRoleByUserId(Long userId) {
        UserRole userRoleParam = new UserRole();
        userRoleParam.setUserId(userId);
        List<UserRole> userRoleList = userRoleService.selectList(userRoleParam).get();
        Long[] ids = new Long[userRoleList.size()];
        for (int i = 0; i < userRoleList.size(); i++) {
            ids[i] = userRoleList.get(i).getRoleId();
        }
        List<Role> roleList = this.selectByIds(ids).get();
        return roleList;
    }

}
