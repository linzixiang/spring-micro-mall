package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.Config;
import com.linzx.admin.system.mapper.ConfigMapper;
import com.linzx.admin.system.service.IConfigService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 参数配置 服务层实现
 * @author linzixiang
 * @date 2020-06-21 12:54:38
 */
@Service("configService")
public class ConfigService extends BaseService<Long, Config, ConfigMapper> implements IConfigService {

    private ConfigMapper configMapper;

    public ConfigService(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Override
    protected ConfigMapper getMapper() {
        return this.configMapper;
    }

}
