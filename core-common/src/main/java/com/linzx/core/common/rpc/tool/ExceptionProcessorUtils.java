package com.linzx.core.common.rpc.tool;

import com.linzx.core.common.exception.BaseException;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.common.rpc.CommonRpcCode;
import com.linzx.core.common.rpc.IMessage;

/**
 * 异常处理
 */
public class ExceptionProcessorUtils {

    public static IMessage wrapperHandlerException(Exception ex){
        if (ex instanceof BaseException) {
            BaseException exception = (BaseException) ex;
            return new IMessage() {
                @Override
                public String getCode() {
                    return exception.getErrorCode();
                }

                @Override
                public String getMessage() {
                    return exception.getMsg();
                }
            };
        }
        return CommonRpcCode.Common.UNKNOW_ERROR_CODE;
    }

}
