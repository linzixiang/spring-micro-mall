package com.linzx.search.service;

import com.linzx.core.common.rpc.CommonRpcRes;
import com.linzx.core.common.rpc.tool.ExceptionProcessorUtils;
import com.linzx.core.framework.base.BaseMapper;
import com.linzx.core.framework.base.BaseService;
import com.linzx.search.IDemoService;
import com.linzx.search.convert.DemoConvert;
import com.linzx.search.domain.Demo;
import com.linzx.search.dto.DemoReqDto;
import com.linzx.search.dto.DemoResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class DemoService extends BaseService implements IDemoService {

    @Autowired
    private DemoConvert demoConvert;

    @Override
    public CommonRpcRes<DemoResDto> hello(DemoReqDto reqDto) {
        DemoResDto demoResDto = new DemoResDto();
        try {
            log.info("-----------------------DemoService--------------------------");
            Demo demo = new Demo();
            demo.setDemo("test");
            demoResDto = demoConvert.demo2ResDto(demo);
        } catch (Exception e) {
            log.error("DemoService.hello Occur Exception :" + e);
            return CommonRpcRes.fail(ExceptionProcessorUtils.wrapperHandlerException(e));
        }
        return CommonRpcRes.ok().setResult(demoResDto);
    }

    @Override
    protected BaseMapper getMapper() {
        return null;
    }
}
