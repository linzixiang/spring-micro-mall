package com.linzx.admin.system.dto.dept.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门 列表查询参数
 * 
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@Getter
@Setter
public class DeptListReq {

    /** 部门名称 */
    private String deptNameLike;
    /** 状态 （1正常 2停用） */
    private Integer status;

}
