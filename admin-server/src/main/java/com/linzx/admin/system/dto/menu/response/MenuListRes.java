package com.linzx.admin.system.dto.menu.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 菜单  列表查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 15:05:14
 */
@Getter
@Setter
public class MenuListRes {

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
    /** 权限标识 */
    private String perms;
    /** 菜单图标 */
    private String icon;
    /** 组件路径 */
    private String component;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
    /** 菜单类型 （M目录 C菜单 F按钮） */
    private String menuType;

}
