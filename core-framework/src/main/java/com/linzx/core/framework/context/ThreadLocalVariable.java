package com.linzx.core.framework.context;

import com.linzx.core.common.bean.UserLoginPrincipal;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程上下文
 */
public class ThreadLocalVariable {

    /** 通用线程变量 **/
    private static final ThreadLocal<Map<String, Object>> threadVariables = new ThreadLocal<>();

    public static final void setVariable(String key, Object value) {
        Map<String, Object> mapVariables = threadVariables.get();
        if (mapVariables == null) {
            mapVariables = new HashMap<>();
            mapVariables.put(key, value);
            threadVariables.set(mapVariables);
        } else {
            mapVariables.put(key, value);
        }
    }

    public static final Object getVariable(String key) {
        Map<String, Object> mapVariables = threadVariables.get();
        if (mapVariables != null) {
            return mapVariables.get(key);
        }
        return null;
    }

    public static final void removeVariable(String key) {
        Map<String, Object> mapVariables = threadVariables.get();
        if (mapVariables != null && mapVariables.containsKey(key)) {
            mapVariables.remove(key);
        }
    }

    public static final void clear() {
        Map<String, Object> mapVariables = threadVariables.get();
        if (mapVariables != null) {
            mapVariables.clear();
        }
        threadVariables.remove();
        threadVariables.set(null);
    }

    /**
     * 设置用户登录凭证
     * @param loginPricipal
     */
    public static final void setUserLoginPricipal(UserLoginPrincipal loginPricipal) {
        setVariable(Constant.USER_LOGIN_PRINCIPAL, loginPricipal);
    }

    /**
     * 获取用户登录凭证
     * @return
     */
    public static final UserLoginPrincipal getUserLoginPricipal() {
        UserLoginPrincipal userLoginPrincipal = (UserLoginPrincipal) getVariable(Constant.USER_LOGIN_PRINCIPAL);
        return userLoginPrincipal;
    }

    public static class Constant {
        public static final String USER_LOGIN_PRINCIPAL = "USER_LOGIN_PRINCIPAL";
    }

}
