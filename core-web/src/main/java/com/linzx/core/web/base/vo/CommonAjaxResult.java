package com.linzx.core.web.base.vo;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.github.pagehelper.Page;
import com.linzx.core.web.base.vo.code.CommonResponseCode;
import com.linzx.core.web.base.vo.code.IMessage;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Date;

/**
 * 请求返回报文
 * @param <T>
 */
public class CommonAjaxResult<T> extends BaseAjaxResult{

    public CommonAjaxResult() {
    }

    public CommonAjaxResult(boolean success, String message) {
        this.setSuccess(success);
        this.setMessage(message);
    }

    public CommonAjaxResult(boolean success) {
        this.setSuccess(success);
    }

    public CommonAjaxResult(String code, String message) {
        this.setStatusCode(code);
        this.setMessage(message);
    }

    public CommonAjaxResult(boolean success, String message, T data) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setData(data);
    }

    public static CommonAjaxResult ok() {
        return ok(CommonResponseCode.Common.SUCCESS);
    }

    public static <T> CommonAjaxResult<T> ok(IMessage msg) {
        return baseCreate(msg.getCode(), msg.getMessage(), msg.getMessageArgs(),true);
    }

    public static CommonAjaxResult fail(String message) {
        return fail(new CommonResponseCode.CommonError(message));
    }

    public static CommonAjaxResult fail(String message, Object... args) {
        return fail(new CommonResponseCode.CommonError(message, args));
    }

    public static CommonAjaxResult fail(IMessage message) {
        return fail(message.getCode(), message.getMessage(), message.getMessageArgs());
    }

    public static CommonAjaxResult fail(String code, String msg, Object[] args) {
        return baseCreate(code, msg, args, false);
    }

    private static <T> CommonAjaxResult<T> baseCreate(String code, String msg, Object[] args, boolean success) {
        CommonAjaxResult result = new CommonAjaxResult();
        result.setStatusCode(code);
        result.setSuccess(success);
        try {
            MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
            String message = messageSource.getMessage(msg, args, null);
            result.setMessage(message);
        } catch (NoSuchMessageException exception) {
            result.setMessage(msg);
        }
        result.setTimestamp(String.valueOf(new Date().getTime()));
        return result;
    }

    public CommonAjaxResult<T> setResult(T data) {
        this.setData(data);
        return this;
    }

    public CommonAjaxResult<T> setListResult(T dataList, Page page) {
        MapBuilder<String, Object> mapBuilder = MapUtil.<String, Object>builder()
                .put("dataList", dataList) // 数据
                .put("totalCount", page.getTotal()) // 总数
                .put("pages", page.getPages()); // 总页数
        if (MapUtil.isNotEmpty(page.getRowSum())) {
            mapBuilder.put("rowSum", page.getRowSum());
        }
        this.setData(mapBuilder.build());
        return this;
    }

    public T getData() {
        return (T) super.getData();
    }

}
