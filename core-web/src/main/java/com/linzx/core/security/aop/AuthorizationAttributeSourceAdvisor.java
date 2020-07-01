package com.linzx.core.security.aop;

import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.security.authz.RequiresRoles;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthorizationAttributeSourceAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private static final Class<? extends Annotation>[] AUTHZ_ANNOTATION_CLASSES =
            new Class[] {
                    RequiresPermissions.class, RequiresRoles.class
            };

    public AuthorizationAttributeSourceAdvisor() {
        setAdvice(new AopAuthorizingMethodInterceptor());
    }

    /**
     * 切入点方法匹配方式
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method m = method;
        if (isAuthzAnnotationPresent(m)) {
            return true;
        }
        if (targetClass != null) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                if (isAuthzAnnotationPresent(m)) {
                    return true;
                }
            } catch (NoSuchMethodException e) {
            }
        }
        return false;
    }

    /**
     * 判断目标方法上是否有注解
     * @param method
     * @return
     */
    private boolean isAuthzAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }

}
