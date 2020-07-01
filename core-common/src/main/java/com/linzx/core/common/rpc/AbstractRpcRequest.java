package com.linzx.core.common.rpc;

import java.io.Serializable;

public abstract class AbstractRpcRequest implements Serializable {

    public AbstractRpcRequest() {
    }

    public abstract void requestCheck();

}
