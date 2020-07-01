package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 岗位 表 sys_post
 * 
 * @author linzixiang
 * @date 2020-06-10 22:35:48
 */
@Getter
@Setter
@Table("sys_post")
public class Post extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long postId;
	/** 岗位编码 */
	private String postCode;
	/** 岗位名称 */
	private String postName;
	/** 排序 （降序） */
	private Integer orderNum;

	@Override
	public void setId(Long id) {
		this.setPostId(id);
	}
	
	@Override
	public Long getId() {
		return this.getPostId();
	}
	
}
