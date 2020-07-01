package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联 表 sys_user_role
 * 
 * @author linzixiang
 * @date 2020-06-10 17:05:18
 */
@Getter
@Setter
@Table("sys_user_role")
public class UserRole extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long useRoleId;
	/** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;

	@Override
	public void setId(Long id) {
		this.setUseRoleId(id);
	}
	
	@Override
	public Long getId() {
		return this.getUseRoleId();
	}
	
}
