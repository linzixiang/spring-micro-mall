package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.RoleMenu;
import com.linzx.admin.system.mapper.RoleMenuMapper;
import com.linzx.admin.system.service.IRoleMenuService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单关联  服务层实现
 * @author linzixiang
 * @date 2020-06-10 17:11:00
 */
@Service("roleMenuService")
public class RoleMenuService extends BaseService<Long, RoleMenu, RoleMenuMapper> implements IRoleMenuService {

    private RoleMenuMapper roleMenuMapper;

    public RoleMenuService(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    protected RoleMenuMapper getMapper() {
        return this.roleMenuMapper;
    }

    @Override
    public List<RoleMenu> selectByRoleIds(Long[] roleId) {
        return null;
    }
}
