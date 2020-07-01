package com.linzx.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import com.linzx.core.web.config.WebFilterProperties;
import com.linzx.core.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;

import static com.linzx.core.web.base.vo.code.CommonResponseCode.Common.*;


/**
 * session会话续期
 */
@Component
public class SessionTokenFilter extends OncePerRequestFilter {

    private PathMatcher patternMatcher = new AntPathMatcher();

    @Autowired
    private WebFilterProperties webFilterProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String requestUri = WebUtils.getPathWithinApplication(request);
        String[] anonUrls = webFilterProperties.getAnonUrls();
        for(String anonUrl : anonUrls) {
            if (patternMatcher.match(anonUrl, requestUri)) {
                // 允许匿名
                chain.doFilter(request, response);
                return;
            }
        }
        String isLogin = (String) session.getAttribute(WebSessionConstant.IS_LOGIN);
        // 必须登录
        if (StrUtil.equals(isLogin, "true")) {
            chain.doFilter(request, response);
            return;
        }
        // 提示客户端登陆
        boolean ajaxRequest = ServletUtil.isAjaxRequest(request);
        if (ajaxRequest) {
            // 如果是ajax请求，返回json
            CommonAjaxResult result = CommonAjaxResult.fail(LOGIN_TIMEOUT);
            String resJson = JSONObject.toJSONString(result);
            ServletUtil.write(response, resJson, ContentType.build(ContentType.JSON.toString(), Charset.forName("utf-8")));
        } else {
            response.sendRedirect(webFilterProperties.getLoginUrl());
        }
    }




}
