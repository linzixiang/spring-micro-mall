package com.linzx.admin.system.dto.dept.request;

import com.linzx.core.web.support.validate.annotation.EnumRange;
import com.linzx.core.web.support.validate.annotation.MobilePhoneNumber;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 部门 修改保存参数
 * 
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@Getter
@Setter
public class DeptEditReq {

    /** 主键 */
    @NotNull
    private Long deptId;
    /** 父部门id */
    private Long parentId;
    /** 部门名称 */
    @Length(max = 10, message = "部门名称deptName长度不能超过10个字符")
    private String deptName;
    /** 显示排序 （降序） */
    private Integer orderNum;
    /** 负责人 */
    private String leader;
    /** 联系电话 */
    @MobilePhoneNumber(message = "联系电话phone格式不正确")
    private String phone;
    /** 邮箱 */
    private String email;
    /** 状态 （1正常 2停用） */
    @EnumRange(value = {"1", "2"}, message = "状态status不合法")
    private Integer status;

}
