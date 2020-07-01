package com.linzx.admin.system.dto.role.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 角色 列表查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 16:33:11
 */
@Getter
@Setter
public class RoleListRes {

    /** 主键 */
    private Long roleId;
    /** 角色编码 */
    private String roleCode;
    /**角色名称**/
    private String roleName;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
}
