package com.linzx.core.web.base.vo;

import java.util.List;

public abstract class BaseTree {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标签
     */
    private String label;

    /**
     * 层级，逗号分隔
     */
    private String ancestors;

    /**
     * 子节点
     */
    private List<BaseTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
