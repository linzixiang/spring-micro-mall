package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.Config;
import com.linzx.admin.system.mapper.ConfigMapper;
import com.linzx.admin.system.service.IConfigService;
import com.linzx.core.framework.base.BaseService;
import com.linzx.core.framework.support.quartz.core.Job;
import com.linzx.core.framework.support.quartz.core.QuartzJobInit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
