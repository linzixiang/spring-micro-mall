package com.linzx.admin.system.dto.role.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色 新增保存参数
 * @author linzixiang
 * @date 2020-06-10 16:33:11
 */
@Getter
@Setter
public class RoleAddReq {

    /** 角色编码 */
    private String roleCode;
    /**角色名称**/
    private String roleName;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 备注 */
    private String remark;
    /**菜单权限**/
    private List<Long> menuId;

}
