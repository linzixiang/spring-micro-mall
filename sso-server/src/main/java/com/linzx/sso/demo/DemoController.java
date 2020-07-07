package com.linzx.sso.demo;

import com.linzx.core.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world！！";
    }

}
