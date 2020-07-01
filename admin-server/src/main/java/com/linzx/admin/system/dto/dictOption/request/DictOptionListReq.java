package com.linzx.admin.system.dto.dictOption.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典选项  列表查询参数
 * 
 * @author linzixiang
 * @date 2020-06-10 15:23:11
 */
@Getter
@Setter
public class DictOptionListReq {

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典编码
     */
    private String dictCode;

}
