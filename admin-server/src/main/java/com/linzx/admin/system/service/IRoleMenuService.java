package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Post;
import com.linzx.admin.system.domain.RoleMenu;

import java.util.List;
import java.util.Optional;

/**
 * 角色菜单关联  服务层接口
 * @author linzixiang
 * @date  2020-06-10 17:11:00
 */
public interface IRoleMenuService {

    /**
     *
     */
    List<RoleMenu> selectByRoleIds(Long[] roleId);

    Optional<RoleMenu> getById(Long id);

    Optional<List<RoleMenu>> selectByIds(Long[] ids);

    Optional<List<RoleMenu>> selectList(RoleMenu roleMenu);

    Integer insert(RoleMenu roleMenu);

    Integer insertBatch(List<RoleMenu> roleMenuList);

    Integer update(RoleMenu roleMenu);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);
}
