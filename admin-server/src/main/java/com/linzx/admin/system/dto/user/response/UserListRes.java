package com.linzx.admin.system.dto.user.response;

import cn.hutool.core.date.DatePattern;
import com.linzx.core.framework.base.annotation.Excel;
import com.linzx.core.framework.base.annotation.ExcelColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户  列表查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 15:01:23
 */
@Getter
@Setter
@Excel(fileName = "用户")
public class UserListRes {

    /** 用户ID */
    private Long userId;
    /** 用户帐号 */
    @ExcelColumn(name = "用户帐号", orderNum = 1)
    private String account;
    /** 部门id **/
    private Long deptId;
    /** 用户昵称 */
    @ExcelColumn(name = "用户昵称", orderNum = 2)
    private String nickName;
    /** 邮箱 */
    @ExcelColumn(name = "用户昵称", orderNum = 3)
    private String email;
    /** 头像 */
    private String avatar;
    /** 手机号 */
    @ExcelColumn(name = "手机号", orderNum = 4)
    private String phoneNumber;
    /** 性别 （1男 2女 3未知） */
    @ExcelColumn(name = "性别", orderNum = 5, dictCode = "normal_gender")
    private Integer sex;
    /** 状态 （1正常 2停用） */
    @ExcelColumn(name = "状态", orderNum = 6, dictCode = "enable_normal_disable")
    private Integer status;
    /** 部门名称 **/
    @ExcelColumn(name = "部门名称", orderNum = 7)
    private String deptName;
    /** 创建时间 */
    @ExcelColumn(name = "创建时间", orderNum = 8)
    private Date createTime;

}
