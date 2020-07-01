package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * 菜单  服务层接口
 * @author linzixiang
 * @date  2020-06-10 15:09:48
 */
public interface IMenuService {

    /**
     * 构建menu层次结构
     * @param menuAll 所有菜单
     * @return
     */
    List<Menu> buildMenuChildren(List<Menu> menuAll);

    /**
     * 查询角色下的菜单
     * @param roleList
     * @return
     */
     List<Menu> selectMenuByRole(List<Role> roleList);

    /**
     * 设置部门层级
     * @param menu
     */
    void setMenuAncestors(Menu menu);

    Optional<Menu> getById(Long id);

    Optional<List<Menu>> selectByIds(Long[] ids);

    Optional<List<Menu>> selectList(Menu menu);

    Integer insert(Menu menu);

    Integer insertBatch(List<Menu> menuList);

    Integer update(Menu menu);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}
