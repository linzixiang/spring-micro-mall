package com.linzx.core.common.rpc;

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

}
