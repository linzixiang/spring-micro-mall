package com.linzx.sso.admin.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.linzx.common.util.PasswordUtils;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.common.constant.RedisKeyConstant;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.common.exception.DBDataException;
import com.linzx.core.framework.redis.RedisCacheService;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import com.linzx.core.web.base.vo.code.CommonResponseCode;
import com.linzx.sso.admin.domain.User;
import com.linzx.sso.admin.dto.request.AdminLoginReq;
import com.linzx.sso.admin.dto.response.AdminLoginRes;
import com.linzx.sso.admin.service.ILoginService;
import com.linzx.sso.admin.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 系统管理登陆控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

    @Autowired
    private ILoginService loginService;

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository;

    /**
     * 登陆
     * @return
     */
    @PostMapping("/login")
    public CommonAjaxResult login(AdminLoginReq adminLoginReq) {
//        loginService.checkCaptcha(adminLoginReq.getCode(), adminLoginReq.getUuid());
        User user = loginService.loginByAccount(adminLoginReq.getUserName(), adminLoginReq.getPassword());
        UserLoginPrincipal principal = new UserLoginPrincipal();
        principal.setUserId(Convert.toStr(user.getUserId()));
        HttpSession session = getRequest().getSession();
        session.setAttribute(WebSessionConstant.IS_LOGIN, "true");
        session.setAttribute(WebSessionConstant.USER_LOGIN_PRINCIPAL, principal);
        AdminLoginRes adminLoginRes = new AdminLoginRes();
        adminLoginRes.setToken(session.getId());
        return CommonAjaxResult.ok().setResult(adminLoginRes);
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public CommonAjaxResult logout() {
        HttpSession session = getRequest().getSession();
        session.invalidate();
        return CommonAjaxResult.ok();
    }


}
