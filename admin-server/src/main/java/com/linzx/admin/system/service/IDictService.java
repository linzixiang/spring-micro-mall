package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Dict;

import java.util.List;
import java.util.Optional;

/**
 * 字典 服务层接口
 * @author linzixiang
 * @date  2020-06-09 09:44:52
 */
public interface IDictService {

    Optional<Dict> getById(Long id);

    Optional<List<Dict>> selectByIds(Long[] ids);

    Optional<List<Dict>> selectList(Dict dict);

    Integer insert(Dict dict);

    Integer insertBatch(List<Dict> dictList);

    Integer update(Dict dict);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);


}
