package com.global.format.response;


import com.global.format.response.constant.PlatformCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultRpcResult<T> implements IRpcResult {
    private static final long serialVersionUID = 8382570691525415842L;
    private String code;
    private String message;
    private String subCode;
    private String subMessage;
    private T data;

    public DefaultRpcResult() {
    }

    @Override
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


    @Override
    public boolean checkSuccess() {
        return PlatformCode.SUCCESS.getCode().equals(this.code);
    }

    @Override
    public String toString() {
        return "DefaultRpcResult(code=" + this.code + ", message=" + this.message + ", subCode=" + this.subCode + ", subMessage=" + this.subMessage + ", data=" + this.data + ")";
    }

    public static DefaultRpcResult successWithoutData() {
        DefaultRpcResult rpcResult = new DefaultRpcResult();
        rpcResult.setCode(PlatformCode.SUCCESS.getCode());
        rpcResult.setMessage(PlatformCode.SUCCESS.getMessage());
        return rpcResult;
    }

    public static <T> IRpcResult<T> successWithData(T data) {
        DefaultRpcResult<T> rpcResult = new DefaultRpcResult();
        rpcResult.setCode(PlatformCode.SUCCESS.getCode());
        rpcResult.setMessage(PlatformCode.SUCCESS.getMessage());
        rpcResult.setData(data);
        return rpcResult;
    }
}
