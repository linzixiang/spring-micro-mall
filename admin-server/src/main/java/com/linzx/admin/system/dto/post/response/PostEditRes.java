package com.linzx.admin.system.dto.post.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 岗位  修改查询响应
 * 
 * @author linzixiang
 * @date 2020-06-10 22:35:48
 */
@Getter
@Setter
public class PostEditRes {

    /** 主键 */
    private Long postId;
    /** 岗位编码 */
    private String postCode;
    /** 岗位名称 */
    private String postName;
    /** 排序 （降序） */
    private Integer orderNum;
    /** 状态 （1正常 2停用） */
    private Integer status;
    /** 备注 */
    private String remark;

}
