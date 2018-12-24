package com.foobar.common.code;


/**
 * 系统级错误码
 */
public enum SysCode implements Code {


    /* 成功状态码 */
    OK(200, "Success"),
    BADREQUEST(400, " BadRequest"),
    NOT_FOUND(404 , " Not Found"),
    UNAUTHORIZED(401, "Unauthorized"),
    ERR(500, "Server Internal Error"),

    /* 系统错误：4001-4999 */
    SYSTEM_INNER_ERROR(4001, "系统繁忙，请稍后重试"),


    /* 接口错误：6001-6999 */
    INTERFACE_INNER_INVOKE_ERROR(6001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(6002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(6003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(6004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(6005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(6006, "接口负载过高"),

    /* 权限错误：7001-7999 */
    PERMISSION_NO_ACCESS(7001, "无访问权限");


    private final Integer code;
    private final String message;

    private SysCode(Integer code, String message) {
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
