package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.DictOption;
import com.linzx.admin.system.dto.dictOption.request.DictOptionAddReq;
import com.linzx.admin.system.dto.dictOption.request.DictOptionEditReq;
import com.linzx.admin.system.dto.dictOption.request.DictOptionListReq;
import com.linzx.admin.system.dto.dictOption.response.DictOptionEditRes;
import com.linzx.admin.system.dto.dictOption.response.DictOptionListRes;
import com.linzx.admin.system.dto.dictOption.response.DictOptionsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * DictOptionController对象转换
 */
@Mapper(componentModel="spring")
public interface DictOptionConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    DictOption dictOptionListRes2DictOption(DictOptionListReq dictOptionListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    DictOptionListRes dictOption2DictOptionListRes(DictOption dictOption);

    /**
     * 新增保存
     */
    @Mappings({})
    DictOption dictOptionAddReq2DictOption(DictOptionAddReq dictOptionAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    DictOptionEditRes dictOption2DictOptionEditRes(DictOption dictOption);

    /**
     * 编辑保存
     */
    @Mappings({})
    DictOption dictOptionEditReq2DictOption(DictOptionEditReq dictOptionEditReq);
    
    @Mappings({})
    DictOptionsDto dictOption2OptionEnableUse(DictOption dictOption);

}
