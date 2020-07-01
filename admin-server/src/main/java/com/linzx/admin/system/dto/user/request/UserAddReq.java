package com.linzx.admin.system.dto.user.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户  新增保存参数
 * @author linzixiang
 * @date 2020-06-10 15:01:23
 */
@Getter
@Setter
public class UserAddReq {

    /** 用户帐号 */
    private String account;
    /** 密码 */
    private String password;
    /** 部门id **/
    private Long deptId;
    /** 用户昵称 */
    private String nickName;
    /** 手机号 */
    private String phoneNumber;
    /** 邮箱 */
    private String email;
    /** 性别 （1男 2女 3 未知） */
    private Integer sex;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 岗位id **/
    private List<Long> postId;
    /** 角色id **/
    private List<Long> roleId;
    /** 备注 */
    private String remark;

}
