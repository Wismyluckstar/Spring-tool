package com.global.format.response;

public interface IRpcResult<T> extends IApiResult<T> {
    boolean checkSuccess();
}
