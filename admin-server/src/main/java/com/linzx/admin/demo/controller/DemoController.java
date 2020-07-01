package com.linzx.admin.demo.controller;

import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.common.rpc.CommonRpcRes;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import com.linzx.search.IDemoService;
import com.linzx.search.dto.DemoReqDto;
import com.linzx.search.dto.DemoResDto;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @Reference(check = false)
    private IDemoService demoService;

    @GetMapping("/test")
    @RequiresPermissions("demo:test:test")
    public CommonAjaxResult test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute(WebSessionConstant.USER_LOGIN_PRINCIPAL);
        return CommonAjaxResult.ok();
    }

//    @SentinelResource(value = "HelloWorld")
    @GetMapping("/sayHelloSentinel")
    @RequiresPermissions("system:user:list")
    public CommonAjaxResult sayHelloSentinel() {
        System.out.println("------------------");
        // RpcContext.getContext().setAttachment("dubboApplication", "sentinelWeb");
//        DemoReqDto demoReqDto = new DemoReqDto();
//        CommonRpcRes<DemoResDto> remoteRes = demoService.hello(demoReqDto);
        return CommonAjaxResult.ok();
    }

}
