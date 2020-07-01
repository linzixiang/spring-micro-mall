package com.linzx.admin.system.dto.post.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 岗位  列表查询参数
 * 
 * @author linzixiang
 * @date 2020-06-10 22:35:48
 */
@Getter
@Setter
public class PostListReq {

    /** 岗位编码 */
    private String postCode;
    /** 岗位名称 */
    private String postName;
    /** 状态 （1正常 2停用） */
    private Integer status;

}
