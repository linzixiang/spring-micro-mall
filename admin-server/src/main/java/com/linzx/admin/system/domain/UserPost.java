package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户岗位关联表 sys_user_post
 * 
 * @author linzixiang
 * @date 2020-06-11 15:13:23
 */
@Getter
@Setter
@Table("sys_user_post")
public class UserPost extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long userPostId;
	/** 用户id */
	private Long userId;
	/** 岗位id */
	private Long postId;

	@Override
	public void setId(Long id) {
		this.setUserPostId(id);
	}
	
	@Override
	public Long getId() {
		return this.getUserPostId();
	}
	
}
