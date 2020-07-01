package com.linzx.core.framework.support.xml.bean;

public class SensitiveBean {

    public static final String UNIQUE_KEYNAME = "code";

    private String code;

    private String name;

    private String formatBeanMethod;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatBeanMethod() {
        return formatBeanMethod;
    }

    public void setFormatBeanMethod(String formatBeanMethod) {
        this.formatBeanMethod = formatBeanMethod;
    }
}
