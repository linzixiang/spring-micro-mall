package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Config;

import java.util.List;
import java.util.Optional;

/**
 * 参数配置 服务层接口
 * @author linzixiang
 * @date  2020-06-21 12:54:38
 */
public interface IConfigService {

    Optional<Config> getById(Long id);

    Optional<List<Config>> selectByIds(Long[] ids);

    Optional<List<Config>> selectList(Config config);

    Integer insert(Config config);

    Integer insertBatch(List<Config> configList);

    Integer update(Config config);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}
