package com.global.format.response.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;
@Getter
public enum PlatformCode {
    PARAM_ERROR("PARAM_ERROR", "parameter error"),
    SYSTEM_ERROR("SYSTEM_ERROR", "system error"),
    BIZ_ERROR("BIZ_ERROR", "business error"),
    SUCCESS("SUCCESS", "ok");

    private String code;
    private String message;
    private static Map<String, PlatformCode> CODE_MAP = (Map)Arrays.stream(values()).collect(Collectors.toMap(PlatformCode::getCode, (e) -> {
        return e;
    }));

    private PlatformCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PlatformCode containsCode(String code) {
        return (PlatformCode)CODE_MAP.get(code);
    }
}
