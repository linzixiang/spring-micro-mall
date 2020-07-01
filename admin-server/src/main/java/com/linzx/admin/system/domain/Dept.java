package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 部门表 sys_dept
 * 
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@Getter
@Setter
@Table("sys_dept")
public class Dept extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long deptId;
	/** 父部门id */
	private Long parentId;
	/** 部门层级 */
	private String ancestors;
	/** 部门名称 */
	private String deptName;
	/** 显示排序 （降序） */
	private Integer orderNum;
	/** 负责人 */
	private String leader;
	/** 联系电话 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 子部门 **/
	private List<Dept> children;

	@Override
	public void setId(Long id) {
		this.setDeptId(id);
	}
	
	@Override
	public Long getId() {
		return this.getDeptId();
	}
	
}
