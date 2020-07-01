package com.linzx.core.framework.support.xml.bean;

public class CodeBean {

    public static final String UNIQUE_KEYNAME = "keyName";

    /** 返回结果的name属性**/
    private String nameField;

    /** 返回结果的id属性**/
    private String valueField;

    /** 查询表**/
    private String tableName;

    /** where查询条件的固定扩展*/
    private String whereExt;

    /** 模糊匹配的属性 **/
    private String searchField;

    /** 排序，例如 name desc **/
    private String orderBy;

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getWhereExt() {
        return whereExt;
    }

    public void setWhereExt(String whereExt) {
        this.whereExt = whereExt;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    /**************************************前端参数 开始*********************************/
    /**
     * 查询关键字
     */
    private String searchKey;
    /**
     * 是否允许分页
     */
    private boolean pageEnable;
    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页的大小
     */
    private Integer pageSize;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean getPageEnable() {
        return pageEnable;
    }

    public void setPageEnable(boolean pageEnable) {
        this.pageEnable = pageEnable;
    }

    /**************************************前端参数 结束*********************************/
}
