package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色菜单关联 表 sys_role_menu
 * 
 * @author linzixiang
 * @date 2020-06-10 17:11:00
 */
@Getter
@Setter
@Table("sys_role_menu")
public class RoleMenu extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long roleMenuId;
	/** 角色ID */
	private Long roleId;
	/** 菜单ID */
	private Long menuId;

	@Override
	public void setId(Long id) {
		this.setRoleMenuId(id);
	}
	
	@Override
	public Long getId() {
		return this.getRoleMenuId();
	}
	
}
