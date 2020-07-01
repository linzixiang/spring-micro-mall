package com.linzx.core.security.handle;

import java.lang.annotation.Annotation;

public abstract class AnnotationHandler {

    protected Class<? extends Annotation> annotationClass;

    public AnnotationHandler(Class<? extends Annotation> annoclass) {
        setAnnotationClass(annoclass);
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {

        if (annotationClass == null) {
            String msg = "annotationClass argument cannot be null";
            throw new IllegalArgumentException(msg);
        }
        this.annotationClass = annotationClass;
    }

}
