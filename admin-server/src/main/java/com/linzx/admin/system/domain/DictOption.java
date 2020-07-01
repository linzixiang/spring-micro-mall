package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典选项 表 sys_dict_option
 * 
 * @author linzixiang
 * @date 2020-06-09 14:27:41
 */
@Getter
@Setter
@Table("sys_dict_option")
public class DictOption extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long dictOptionId;
	/** 字典主表 */
	private Long dictId;
	/** 字典编码 */
	private String dictCode;
	/** 字典标签 */
	private String dictLabel;
	/** 字典键值 */
	private String dictValue;
	/** 样式属性 （其他样式扩展） */
	private String cssClass;
	/** 表格回显样式 */
	private String listClass;
	/** 字典排序 （降序） */
	private Integer orderNum;

	@Override
	public void setId(Long id) {
		this.setDictOptionId(id);
	}
	
	@Override
	public Long getId() {
		return this.getDictOptionId();
	}
	
}
