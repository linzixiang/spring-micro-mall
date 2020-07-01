package com.linzx.core.framework.support.xml.bean;

/**
 * 字典bean
 */
public class DictBean {

    public static final String UNIQUE_KEYNAME = "keyName";

    /** 字典唯一编码*/
    private String dictCode;

    /** 字典where条件扩展 **/
    private String whereExt;

    /** 字典是否包含停用的选项**/
    private Boolean excludeStop;

    /** 字典是否包含删除的选项 **/
    private Boolean excludeDelete;

    /** 排序 **/
    private String orderBy;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getWhereExt() {
        return whereExt;
    }

    public void setWhereExt(String whereExt) {
        this.whereExt = whereExt;
    }

    public Boolean getExcludeStop() {
        return excludeStop;
    }

    public void setExcludeStop(Boolean excludeStop) {
        this.excludeStop = excludeStop;
    }

    public Boolean getExcludeDelete() {
        return excludeDelete;
    }

    public void setExcludeDelete(Boolean excludeDelete) {
        this.excludeDelete = excludeDelete;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
