package com.linzx.admin.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.domain.Role;
import com.linzx.admin.system.domain.RoleMenu;
import com.linzx.admin.system.mapper.MenuMapper;
import com.linzx.admin.system.service.IMenuService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单  服务层实现
 * @author linzixiang
 * @date 2020-06-10 15:09:48
 */
@Service("menuService")
public class MenuService extends BaseService<Long, Menu, MenuMapper> implements IMenuService {

    @Autowired
    private RoleMenuService roleMenuService;

    private MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    protected MenuMapper getMapper() {
        return this.menuMapper;
    }

    /**
     * 构建菜单层次结构
     */
    public List<Menu> buildMenuChildren(List<Menu> menuAll) {
        Menu rootMenu = new Menu();
        rootMenu.setId(0L);
        rootMenu.setParentId(-1L);
        rootMenu.setChildren(new ArrayList<>());
        setMenuChildren(rootMenu, menuAll);
        return rootMenu.getChildren();
    }

    /**
     * 递归设置children
     */
    private void setMenuChildren(Menu parentMenu, List<Menu> menuAll) {
        for(Menu menu : menuAll) {
            if (ObjectUtil.equal(menu.getParentId(), parentMenu.getId())) {
                List<Menu> children = parentMenu.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parentMenu.setChildren(children);
                }
                children.add(menu);
            }
        }
        List<Menu> children = parentMenu.getChildren();
        if (children != null && children.size() > 0) {
            for(Menu menu : children) {
                setMenuChildren(menu, menuAll);
            }
        }
    }

    @Override
    public List<Menu> selectMenuByRole(List<Role> roleList) {
        if (CollectionUtil.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        for (Role role : roleList) {
//            if (!Role.DATA_SCOPE_ALL.equals(role.getMenuScope())) {
//                continue;
//            }
            // 所有菜单权限
            Menu menuParams = new Menu();
            menuParams.setStatus(Menu.STATUS_NORMAL);
            menuParams.setVisible(Menu.VISIBLE_SHOW);
            return menuMapper.selectList(menuParams);
        }
        Long[] roleIdArr = new Long[roleList.size()];
        for (int i = 0; i < roleList.size(); i++) {
            roleIdArr[i] = roleList.get(i).getRoleId();
        }
        List<RoleMenu> roleMenus = roleMenuService.selectByRoleIds(roleIdArr);
        // menuId去重
        Set<Long> menuIdSet = new HashSet<>();
        for (RoleMenu roleMenu : roleMenus) {
            menuIdSet.add(roleMenu.getMenuId());
        }
        // menuId 转Long数组
        Long[] menuIds = menuIdSet.toArray(new Long[menuIdSet.size()]);
        List<Menu> menuList = menuMapper.selectByIds(menuIds);
        return menuList;
    }

    @Override
    public void setMenuAncestors(Menu menu) {
        if (menu.getParentId() == null || menu.getParentId() < 0) {
            // parentId不合法
            return;
        }
        if (ObjectUtil.equal(menu.getParentId(), 0)) {
            // 顶层Menu
            menu.setAncestors(Convert.toStr(menu.getId()));
            return;
        }
        Menu parentMenu = this.getById(menu.getParentId()).get();
        menu.setAncestors(parentMenu.getAncestors() + "," + menu.getId());
    }

}
