package com.linzx.core.security.aop;

import com.linzx.core.security.handle.AnnotationHandler;
import com.linzx.core.security.handle.AuthorizingAnnotationHandler;
import com.linzx.core.security.handle.impl.PermissionAnnotationHandler;
import com.linzx.core.security.handle.impl.RoleAnnotationHandler;
import com.linzx.core.web.exception.UnauthorizedException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AopAuthorizingMethodInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(AopAuthorizingMethodInterceptor.class);

    private List<AnnotationHandler> handlers;

    public AopAuthorizingMethodInterceptor() {
        List<AnnotationHandler> handlerList = new ArrayList<>();
        handlerList.add(new PermissionAnnotationHandler());
        handlerList.add(new RoleAnnotationHandler());
        setHandlers(handlerList);
    }

    public List<AnnotationHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<AnnotationHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        assertAuthorized(methodInvocation);
        return methodInvocation.proceed();
    }

    /**
     * 验证权限
     * @param mi
     */
    public void assertAuthorized(MethodInvocation mi) {
        Collection<AnnotationHandler> aaHandlers = getHandlers();
        if (aaHandlers != null && !aaHandlers.isEmpty()) {
            for (AnnotationHandler handler : aaHandlers) {
                try {
                    ((AuthorizingAnnotationHandler) handler).assertAuthorized(getAnnotation(mi, handler));
                } catch (UnauthorizedException ae) {
                    logger.error("权限验证不通过", ae);
                    throw ae;
                }
            }
        }

    }

    private Annotation getAnnotation(MethodInvocation mi, AnnotationHandler handler) {
        Class<? extends Annotation> clazz = handler.getAnnotationClass();
        Method m = mi.getMethod();
        Annotation a = AnnotationUtils.findAnnotation(m, clazz);
        if (a != null) return a;
        Class<?> targetClass = mi.getThis().getClass();
        m = ClassUtils.getMostSpecificMethod(m, targetClass);
        a = AnnotationUtils.findAnnotation(m, clazz);
        if (a != null) return a;
        // See if the class has the same annotation
        return AnnotationUtils.findAnnotation(mi.getThis().getClass(), clazz);
    }

}
