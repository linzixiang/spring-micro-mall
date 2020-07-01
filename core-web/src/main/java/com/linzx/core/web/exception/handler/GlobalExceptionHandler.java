package com.linzx.core.web.exception.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import com.linzx.core.common.constant.ErrorCodeConstant;
import com.linzx.core.common.exception.BaseException;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import static com.linzx.core.web.base.vo.code.CommonResponseCode.Common.*;

/**
 * 全局异常处理
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private ObjectError objectError;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean ajaxRequest = ServletUtil.isAjaxRequest(request);
        logger.error("异常：", ex);
        if (ajaxRequest) {
            CommonAjaxResult result = null;
            if (ex instanceof BaseException) {
                BaseException exception = (BaseException) ex;
                result = CommonAjaxResult.fail(exception.getErrorCode(), exception.getMsg(), exception.getArgs());
            } else if (ex instanceof ServletException) {
                result = CommonAjaxResult.fail(INNER_SERVER_ERROR);
            } else if (ex instanceof SQLException || ex instanceof DuplicateKeyException) {
                result = CommonAjaxResult.fail(ErrorCodeConstant.INNER_SERVER_ERROR_CODE, "db.sql.exec.error", new Object[0]);
            } else if (ex instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
                List<ObjectError> errors = exception.getBindingResult().getAllErrors();
                if (CollectionUtil.isNotEmpty(errors)) {
                    result = CommonAjaxResult.fail("request.param.error",
                            new Object[] {errors.get(0).getDefaultMessage()});
                }
            } else if (ex instanceof ValidationException) {
                ValidationException exception = (ValidationException) ex;
                result = CommonAjaxResult.fail("request.param.error",
                        new Object[] {exception.getMessage()});
            } else if (ex instanceof BindException) {
                BindException exception = (BindException) ex;
                List<ObjectError> errors = exception.getAllErrors();
                if (CollectionUtil.isNotEmpty(errors)) {
                    result = CommonAjaxResult.fail("request.param.error",
                            new Object[] {errors.get(0).getDefaultMessage()});
                }
            } else {
                result = CommonAjaxResult.fail(UNKNOW_ERROR);
            }
            String resJson = JSONObject.toJSONString(result);
            ServletUtil.write(response, resJson, ContentType.build(ContentType.JSON.toString(), Charset.forName("utf-8")));
        }
        ServletUtil.write(response, ExceptionUtil.stacktraceToOneLineString(ex), ContentType.build(ContentType.TEXT_HTML.toString(), Charset.forName("utf-8")));
        return new ModelAndView();
    }

}
