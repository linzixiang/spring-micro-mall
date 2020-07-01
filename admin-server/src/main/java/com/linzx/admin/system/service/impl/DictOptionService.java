package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.DictOption;
import com.linzx.admin.system.mapper.DictOptionMapper;
import com.linzx.admin.system.service.IDictOptionService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典选项  服务层实现
 * @author linzixiang
 * @date 2020-06-10 15:23:11
 */
@Service("dictOptionService")
public class DictOptionService extends BaseService<Long, DictOption, DictOptionMapper> implements IDictOptionService {

    private DictOptionMapper dictOptionMapper;

    public DictOptionService(DictOptionMapper dictOptionMapper) {
        this.dictOptionMapper = dictOptionMapper;
    }

    @Override
    protected DictOptionMapper getMapper() {
        return this.dictOptionMapper;
    }

}
