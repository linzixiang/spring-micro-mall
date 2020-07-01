package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 表 sys_user
 * 
 * @author linzixiang
 * @date 2020-06-03 21:22:45
 */
@Getter
@Setter
@Table("sys_user")
public class User extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_1 = 1;
	public static final Integer USER_TYPE_2 = 2;
	
	/** 用户ID */
	private Long userId;
	/** 用户帐号 */
	private String account;
	/** 部门id **/
	private Long deptId;
	/** 用户昵称 */
	private String nickName;
	/** 用户类型 （1超级用户 2普通用户） */
	private Integer userType;
	/** 密码 */
	private String password;
	/** 密码加盐 */
	private String salt;
	/** 邮箱 */
	private String email;
	/** 头像 */
	private String avatar;
	/** 手机号 */
	private String phoneNumber;
	/** 性别 （1男 2女 3未知） */
	private Integer sex;
	/** 部门名称 **/
	private String deptName;

	@Override
	public void setId(Long id) {
		this.setUserId(id);
	}

	@Override
	public Long getId() {
		return this.getUserId();
	}
}
