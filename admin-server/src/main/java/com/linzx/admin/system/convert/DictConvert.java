package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Dict;
import com.linzx.admin.system.dto.dict.request.DictAddReq;
import com.linzx.admin.system.dto.dict.request.DictEditReq;
import com.linzx.admin.system.dto.dict.request.DictListReq;
import com.linzx.admin.system.dto.dict.response.DictDetailRes;
import com.linzx.admin.system.dto.dict.response.DictEditRes;
import com.linzx.admin.system.dto.dict.response.DictListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Dict对象转换
 */
@Mapper(componentModel="spring")
public interface DictConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Dict dictListRes2Dict(DictListReq dictListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    DictListRes dict2DictListRes(Dict dict);

    /**
     * 详情查询
     */
    @Mappings({})
    DictDetailRes dict2DictDetailRes(Dict dict);

    /**
     * 新增保存
     */
    @Mappings({})
    Dict dictAddReq2Dict(DictAddReq dictAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    DictEditRes dict2DictEditRes(Dict dict);

    /**
     * 编辑保存
     */
    @Mappings({})
    Dict dictEditReq2Dict(DictEditReq dictEditReq);
    
}
