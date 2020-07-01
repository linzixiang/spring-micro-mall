package com.linzx.search.convert;

import com.linzx.search.domain.Demo;
import com.linzx.search.dto.DemoResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * 映射转换
 */
@Mapper(componentModel="spring")
public interface DemoConvert {

    @Mappings({

    })
    DemoResDto demo2ResDto(Demo demo);

}
