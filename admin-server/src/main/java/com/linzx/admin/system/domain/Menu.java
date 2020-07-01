package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单 表 sys_menu
 * 
 * @author linzixiang
 * @date 2020-06-04 21:09:37
 */
@Getter
@Setter
@Table("sys_menu")
public class Menu extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单类型
	 */
	public static final String  MENU_TYPE_M = "M"; // 目录
	public static final String  MENU_TYPE_C = "C"; // 菜单
	public static final String  MENU_TYPE_F = "F"; // 按钮
	/**
	 * 菜单可见性
	 */
	public static final Integer VISIBLE_HIDDEN = 0;
	public static final Integer VISIBLE_SHOW = 1;
	
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
	/** 菜单层级 （逗号分隔，例如：1,2,3） */
	private String ancestors;
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
	/** 子菜单 **/
	private List<Menu> children;

	@Override
	public void setId(Long id) {
		this.setMenuId(id);
	}

	@Override
	public Long getId() {
		return this.getMenuId();
	}
}
