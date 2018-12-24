package com.foobar.common.code;


/**
 * 业务级错误码
 */
public enum AppCode implements Code {


    PARAM_IS_INVALID(1001, "参数无效");


    private final Integer code;
    private final String message;

    private AppCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
