package com.linzx.core.web.interceptor;

import cn.hutool.core.util.StrUtil;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.framework.context.ThreadLocalVariable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 设置用户登陆信息到线程中，方便获取
 */
public class SessionTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String isLogin = (String) session.getAttribute(WebSessionConstant.IS_LOGIN);
        if (StrUtil.equals(isLogin, "true")) {
            UserLoginPrincipal userLoginPrincipal = (UserLoginPrincipal) session.getAttribute(WebSessionConstant.USER_LOGIN_PRINCIPAL);
            ThreadLocalVariable.setUserLoginPricipal(userLoginPrincipal);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalVariable.clear();
    }
}
