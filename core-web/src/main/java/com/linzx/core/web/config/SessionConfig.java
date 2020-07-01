package com.linzx.core.web.config;

import cn.hutool.core.util.ObjectUtil;
import com.linzx.core.common.constant.WebSessionConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * session会话
 */
@Configuration
public class SessionConfig {


    /**
     * 会话id解析策略，解决cookie跨域问题
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionStrategy() {
        return new MyHttpSessionIdResolver();
    }

    /**
     * 组合cookie和header两种方式读取session
     */
    public class MyHttpSessionIdResolver implements HttpSessionIdResolver {

        /**
         * cookie解析
         */
        private CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();

        /**
         * 从http头中解析
         */
        private HeaderHttpSessionIdResolver headerHttpSessionIdResolver = new HeaderHttpSessionIdResolver(WebSessionConstant.HEADER_X_AUTH_TOKEN);

        @Override
        public List<String> resolveSessionIds(HttpServletRequest request) {
            List<String> sessionIds = headerHttpSessionIdResolver.resolveSessionIds(request);
            if (ObjectUtil.isEmpty(sessionIds)) {
                sessionIds = cookieHttpSessionIdResolver.resolveSessionIds(request);
            }
            return sessionIds;
        }

        @Override
        public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
            headerHttpSessionIdResolver.setSessionId(request, response, sessionId);
            cookieHttpSessionIdResolver.setSessionId(request, response, sessionId);
        }

        @Override
        public void expireSession(HttpServletRequest request, HttpServletResponse response) {
            headerHttpSessionIdResolver.expireSession(request, response);
            cookieHttpSessionIdResolver.expireSession(request, response);
        }
    }

}
