package com.linzx.search;

import com.linzx.core.common.rpc.CommonRpcRes;
import com.linzx.search.dto.DemoReqDto;
import com.linzx.search.dto.DemoResDto;

public interface IDemoService {

    CommonRpcRes<DemoResDto> hello(DemoReqDto reqDto);

}
