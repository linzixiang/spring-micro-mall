package com.linzx.admin.system.dto.dict.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DictAddReq {

    /** 字典名称 */
    private String dictName;
    /** 字典编码 */
    private String dictCode;
    /** 备注 */
    private String remark;
    /** 状态 （1正常 2停用） */
    private Integer status;


}
