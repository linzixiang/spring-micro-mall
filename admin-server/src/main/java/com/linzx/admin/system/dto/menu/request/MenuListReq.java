package com.linzx.admin.system.dto.menu.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单  列表查询参数
 * 
 * @author linzixiang
 * @date 2020-06-10 15:05:14
 */
@Getter
@Setter
public class MenuListReq {

    /** 菜单名称 */
    private String menuNameLike;

    /** 状态 （1正常 2停用） */
    private Integer status;

}
