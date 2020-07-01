package com.linzx.core.web.base.vo.code;

public interface IMessage {

    /**
     * 错误码
     * @return
     */
    String getCode();

    /**
     * 错误描述
     * @return
     */
    String getMessage();

    /**
     * 错误描述的参数
     * @return
     */
    Object[] getMessageArgs();

}
