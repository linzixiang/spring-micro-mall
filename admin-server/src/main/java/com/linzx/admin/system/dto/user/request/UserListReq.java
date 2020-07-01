package com.linzx.admin.system.dto.user.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户  列表查询参数
 * 
 * @author linzixiang
 * @date 2020-06-10 15:01:23
 */
@Getter
@Setter
public class UserListReq {

    /** 部门id **/
    private Long deptId;
    /** 用户昵称 */
    private String nickNameLike;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 创建起始时间，例如：2019-07-24 **/
    private String createTimeGte = "";
    /** 创建结束时间 **/
    private String createTimeLte = "";
}
