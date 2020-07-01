package com.linzx.admin.system.domain;

import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 参数配置表 sys_config
 * 
 * @author linzixiang
 * @date 2020-06-21 12:54:38
 */
@Getter
@Setter
@Table("sys_config")
public class Config extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long configId;
	/** 参数名称 */
	private String configName;
	/** 参数键名 */
	private String configKey;
	/** 参数键值 */
	private String configValue;
	/** 系统内置 （1是 0否） */
	private String configType;

	@Override
	public void setId(Long id) {
		this.setConfigId(id);
	}
	
	@Override
	public Long getId() {
		return this.getConfigId();
	}
	
}
