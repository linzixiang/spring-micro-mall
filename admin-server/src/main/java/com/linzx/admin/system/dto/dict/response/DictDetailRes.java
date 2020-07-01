package com.linzx.admin.system.dto.dict.response;

import lombok.Data;

/**
 * 字典详情
 */
@Data
public class DictDetailRes {

    /** 字典名称 */
    private String dictName;
    /** 字典编码 */
    private String dictCode;
    /** 备注 */
    private String remark;

}
