package com.linzx.admin.system.dto.userInfo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVo {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String userName;

}
