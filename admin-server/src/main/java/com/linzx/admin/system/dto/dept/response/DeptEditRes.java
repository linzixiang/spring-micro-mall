package com.linzx.admin.system.dto.dept.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门 修改查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@Getter
@Setter
public class DeptEditRes {

    /** 主键 */
    private Long deptId;
    /** 父部门id */
    private Long parentId;
    /** 部门名称 */
    private String deptName;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 负责人 */
    private String leader;
    /** 联系电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 状态 （1正常 2停用） */
    private Integer status;

}
