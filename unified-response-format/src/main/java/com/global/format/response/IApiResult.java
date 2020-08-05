package com.global.format.response;

import java.io.Serializable;

public interface IApiResult<T> extends Serializable {
    String getCode();

    String getMessage();

    String getSubCode();

    String getSubMessage();

    T getData();
}