package com.linzx.admin.system.dto.dict.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 字典列表查询条件
 */
@Setter
@Getter
public class DictListReq {

    /** 字典名称 */
    private String dictName;
    /** 字典编码 */
    private String dictCode;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 创建时间 */
    private Date createTime;

}
