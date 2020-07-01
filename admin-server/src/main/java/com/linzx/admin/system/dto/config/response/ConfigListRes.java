package com.linzx.admin.system.dto.config.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 参数配置 列表查询响应
 * 
 * @author linzixiang
 * @date 2020-06-21 12:54:38
 */
@Getter
@Setter
public class ConfigListRes {

    /** 主键 */
    private Long configId;
    /** 参数名称 */
    private String configName;
    /** 参数键名 */
    private String configKey;
    /** 参数键值 */
    private String configValue;
    /** 系统内置 （1是 0否） */
    private String configType;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    private Date createTime;

}
