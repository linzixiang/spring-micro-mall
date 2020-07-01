package com.linzx.admin.system.dto.menu.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单  修改查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 15:05:14
 */
@Getter
@Setter
public class MenuEditRes {

    /** 菜单ID */
    private Long menuId;
    /** 菜单名称 */
    private String menuName;
    /** 父菜单ID */
    private Long parentId;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 路由地址 **/
    private String path;
    /** 菜单类型 （M目录 C菜单 F按钮） */
    private String menuType;
    /** 权限标识 */
    private String perms;
    /** 菜单图标 */
    private String icon;
    /** 组件路径 */
    private String component;
    /** 菜单可见性 （0隐藏 1显示） */
    private Integer visible;

}
