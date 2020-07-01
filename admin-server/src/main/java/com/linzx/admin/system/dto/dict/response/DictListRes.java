package com.linzx.admin.system.dto.dict.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 字典列表展示
 */
@Getter
@Setter
public class DictListRes {

    /** 主键 */
    private Long dictId;
    /** 字典名称 */
    private String dictName;
    /** 字典编码 */
    private String dictCode;
    /** 是否停用 （1正常 2停用） */
    private Integer status;
    /** 锁版本号 */
    private Integer reversion;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    private Date createTime;

}
