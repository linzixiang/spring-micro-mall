package com.linzx.admin.system.dto.dictOption.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 字典选项  列表查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 15:23:11
 */
@Getter
@Setter
public class DictOptionListRes {

    /** 字典编码 */
    private String dictCode;
    /** 字典标签 */
    private String dictLabel;
    /** 字典键值 */
    private String dictValue;
    /** 字典排序 （降序） */
    private Integer orderNum;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
    /** 备注 */
    private String remark;

}
