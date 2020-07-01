package com.linzx.core.web.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 报文基类
 * @param <T>
 */
@Getter
@Setter
public abstract class BaseAjaxResult<T> implements Serializable {

    /**
     * 成功true，失败true
     */
    private boolean success = false;

    /**
     * 描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 时间戳
     */
    private String timestamp;

}
