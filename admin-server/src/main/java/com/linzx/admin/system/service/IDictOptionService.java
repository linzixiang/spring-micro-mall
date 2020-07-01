package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Dept;
import com.linzx.admin.system.domain.DictOption;

import java.util.List;
import java.util.Optional;

/**
 * 字典选项  服务层接口
 * @author linzixiang
 * @date  2020-06-09 14:27:41
 */
public interface IDictOptionService {

    Optional<DictOption> getById(Long id);

    Optional<List<DictOption>> selectByIds(Long[] ids);

    Optional<List<DictOption>> selectList(DictOption dictOption);

    Integer insert(DictOption dictOption);

    Integer insertBatch(List<DictOption> dictOptionList);

    Integer update(DictOption dictOption);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}
