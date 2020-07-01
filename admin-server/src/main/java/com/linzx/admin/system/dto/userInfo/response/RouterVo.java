package com.linzx.admin.system.dto.userInfo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class RouterVo {

    private Boolean alwaysShow;

    private String component;

    private Boolean hidden;

    private String name;

    private String path;

    private String redirect;

    private List<RouterVo> children;

    private RouterMeta meta;

}
