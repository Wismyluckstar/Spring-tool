package com.global.format.response;


import com.global.format.response.constant.PlatformCode;

public class DefaultHttpResult<T> implements IHttpResult<T> {
    private static final long serialVersionUID = 3680093056271909314L;
    private String code;
    private String message;
    private String subCode;
    private String subMessage;
    private T data;

    public DefaultHttpResult() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        if (PlatformCode.containsCode(code) == null) {
            throw new IllegalArgumentException("Invalid code, current code=" + code);
        } else {
            this.code = code;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "DefaultRpcResult(code=" + this.code + ", message=" + this.message + ", subCode=" + this.subCode + ", subMessage=" + this.subMessage + ", data=" + this.data + ")";
    }

    public static <T> IHttpResult<T> successWithoutData() {
        DefaultHttpResult<T> httpResult = new DefaultHttpResult();
        httpResult.setCode(PlatformCode.SUCCESS.getCode());
        httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
        return httpResult;
    }

    public static <T> IHttpResult<T> successWithData(T data) {
        DefaultHttpResult<T> httpResult = new DefaultHttpResult();
        httpResult.setCode(PlatformCode.SUCCESS.getCode());
        httpResult.setMessage(PlatformCode.SUCCESS.getMessage());
        httpResult.setData(data);
        return httpResult;
    }
}
