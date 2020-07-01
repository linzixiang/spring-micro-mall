package com.linzx.admin.system.dto.dict.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Dict编辑保存
 */
@Setter
@Getter
public class DictEditReq {

    /** 主键 */
    private Long dictId;
    /** 字典名称 */
    private String dictName;
    /** 备注 */
    private String remark;
    /** 状态 （1正常 2停用） */
    private Integer status;

}
