package com.linzx.core.web.base.vo;

import java.util.List;

/**
 * 字典
 */
public class DictVo {

    /**
     * 编码名称
     */
    private String codeName;

    /**
     * 选项
     */
    private List<DictOption> options;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public List<DictOption> getOptions() {
        return options;
    }

    public void setOptions(List<DictOption> options) {
        this.options = options;
    }
}
