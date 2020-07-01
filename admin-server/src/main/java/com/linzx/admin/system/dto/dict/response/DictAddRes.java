package com.linzx.admin.system.dto.dict.response;

import com.linzx.admin.system.dto.dictOption.response.DictOptionsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DictAddRes {

    /**
     * 字典状态选项
     */
    private List<DictOptionsDto> statusOptions;

}
