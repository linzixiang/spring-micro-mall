package com.linzx.core.security.handle;

import java.lang.annotation.Annotation;

public abstract class AuthorizingAnnotationHandler extends AnnotationHandler{

    public AuthorizingAnnotationHandler(Class<? extends Annotation> annoclass) {
        super(annoclass);
    }

    /**
     * 处理权限
     */
    public abstract void assertAuthorized(Annotation a);
    
}
