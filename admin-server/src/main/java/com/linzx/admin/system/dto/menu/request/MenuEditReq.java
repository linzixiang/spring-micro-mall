package com.linzx.admin.system.dto.menu.request;

import com.linzx.core.web.support.validate.annotation.EnumRange;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 菜单  修改保存参数
 * 
 * @author linzixiang
 * @date 2020-06-10 15:05:14
 */
@Getter
@Setter
public class MenuEditReq {

    /** 菜单ID */
    @NotNull
    private Long menuId;
    /** 菜单名称 */
    @Length(max = 10, message = "菜单名称menuName长度不能超过10个字符")
    private String menuName;
    /** 父菜单ID */
    private Long parentId;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 路由地址 **/
    private String path;
    /** 菜单类型 （M目录 C菜单 F按钮） */
    @EnumRange(message = "menuType不合法", value = { "M", "C", "F"})
    private String menuType;
    /** 权限标识 */
    private String perms;
    /** 菜单图标 */
    private String icon;
    /** 组件路径 */
    private String component;
    /** 菜单可见性 （0隐藏 1显示） */
    @EnumRange(message = "visible不合法", value = { "0", "1"})
    private Integer visible;

}
