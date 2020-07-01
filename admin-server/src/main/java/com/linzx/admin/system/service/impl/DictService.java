package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.Dict;
import com.linzx.admin.system.domain.DictOption;
import com.linzx.admin.system.mapper.DictMapper;
import com.linzx.admin.system.service.IDictService;
import com.linzx.core.common.exception.DBDataException;
import com.linzx.core.framework.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务层实现
 * @author linzixiang
 * @date 2020-05-18 21:26:47
 */
@Service("dictService")
public class DictService extends BaseService<Long, Dict, DictMapper> implements IDictService {

    private DictMapper dictMapper;

    public DictService(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    protected DictMapper getMapper() {
        return this.dictMapper;
    }

}
