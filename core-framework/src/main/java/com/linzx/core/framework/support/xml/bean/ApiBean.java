package com.linzx.core.framework.support.xml.bean;

/**
 * apiKey方式请求配置
 */
public class ApiBean {

    public static final String UNIQUE_KEYNAME = "id";

    private String urlMap;

    private String desc;

    public String getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(String urlMap) {
        this.urlMap = urlMap;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
