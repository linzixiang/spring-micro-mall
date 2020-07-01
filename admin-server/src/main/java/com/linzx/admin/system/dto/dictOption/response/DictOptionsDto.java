package com.linzx.admin.system.dto.dictOption.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询字典选项，用于翻译
 */
@Getter
@Setter
public class DictOptionsDto {

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

}
