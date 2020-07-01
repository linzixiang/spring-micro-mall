package ${basePackageName}.${projectName}.${moduleName}.domain;

import ${basePackageName}.core.framework.base.BaseEntity;
import com.linzx.core.framework.base.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ${tableComment}表 ${tableName}
 * 
 * @author ${author}
 * @date ${datetime}
 */
@Getter
@Setter
@Table("${tableName}")
public class ${className} extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	<#list columns as columnInfo>
	<#if columnInfo.attrname != 'status' && columnInfo.attrname != 'createBy' && columnInfo.attrname != 'createTime' &&
		columnInfo.attrname != 'updateBy' && columnInfo.attrname != 'updateTime' && columnInfo.attrname != 'reversion' && columnInfo.attrname != 'remark'>
	/** ${columnInfo.columnComment} */
	private ${columnInfo.attrType} ${columnInfo.attrname};
	</#if>
	</#list>

	@Override
	public void setId(Long id) {
		this.set${primaryKey.attrName}(id);
	}
	
	@Override
	public Long getId() {
		return this.get${primaryKey.attrName}();
	}
	
}
