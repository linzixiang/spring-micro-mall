package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色表 sys_role
 * 
 * @author linzixiang
 * @date 2020-06-10 16:33:11
 */
@Getter
@Setter
@Table("sys_role")
public class Role extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	public static final String DATA_SCOPE_ALL = "1"; // 全部菜单权限
	public static final String DATA_SCOPE_CUSTOMIZE = "2"; // 自定义权限
	public static final String DATA_SCOPE_DEPARTMENT  = "3"; // 本部门数据权限
	public static final String DATA_SCOPE_DEPARTMENT_BELOW = "4"; // 本部门及以下数据权限

	/** 主键 */
	private Long roleId;
	/** 角色编码 */
	private String roleCode;
	/**角色名称**/
	private String roleName;
	/** 显示排序 （降序） */
	private Integer orderNum;
	/** 数据范围 （1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限） */
	private String dataScope;

	@Override
	public void setId(Long id) {
		this.setRoleId(id);
	}
	
	@Override
	public Long getId() {
		return this.getRoleId();
	}
	
}
