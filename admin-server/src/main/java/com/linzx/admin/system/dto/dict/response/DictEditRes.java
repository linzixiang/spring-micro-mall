package com.linzx.admin.system.dto.dict.response;

import com.linzx.admin.system.dto.dictOption.response.DictOptionsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Dict编辑查询
 */
@Setter
@Getter
public class DictEditRes {

    /** 主键 */
    private Long dictId;
    /** 字典名称 */
    private String dictName;
    /** 字典编码 */
    private String dictCode;
    /** 备注 */
    private String remark;
    /** 是否停用 */
    private Integer status;
    /** 是否停用字典选项 **/
    private List<DictOptionsDto> statusOptions;

}
