package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Config;
import com.linzx.admin.system.dto.config.request.ConfigAddReq;
import com.linzx.admin.system.dto.config.request.ConfigEditReq;
import com.linzx.admin.system.dto.config.request.ConfigListReq;
import com.linzx.admin.system.dto.config.response.ConfigEditRes;
import com.linzx.admin.system.dto.config.response.ConfigListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Config对象转换
 */
@Mapper(componentModel="spring")
public interface ConfigConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Config configListRes2Config(ConfigListReq configListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    ConfigListRes config2ConfigListRes(Config config);

    /**
     * 新增保存
     */
    @Mappings({})
    Config configAddReq2Config(ConfigAddReq configAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    ConfigEditRes config2ConfigEditRes(Config config);

    /**
     * 编辑保存
     */
    @Mappings({})
    Config configEditReq2Config(ConfigEditReq configEditReq);
    
}
