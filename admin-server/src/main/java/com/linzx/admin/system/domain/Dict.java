package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 字典表 sys_dict
 * 
 * @author linzixiang
 * @date 2020-06-09 09:44:52
 */
@Getter
@Setter
@Table("sys_dict")
public class Dict extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long dictId;
	/** 字典名称 */
	private String dictName;
	/** 字典编码 */
	private String dictCode;
	/** 字典选项 **/
	private List<DictOption> dictOptions;

	@Override
	public void setId(Long id) {
		this.setDictId(id);
	}
	
	@Override
	public Long getId() {
		return this.getDictId();
	}
	
}
