package com.yuneke.common;


import com.yuneke.enums.SystemCodeEnum;
import com.yuneke.interfaces.common.Code;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    private Result() {
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     * @see Result#restResult(Integer, String, Object)
     */
    @Deprecated
    private static <T> Result<T> restResult(Integer code, String msg, T data) {
        Result<T> apiResult = new Result(code, msg, data);
        return apiResult;
    }

    private static <T> Result<T> restResult(Code code, T data) {
        Result<T> apiResult = new Result(code.getCode(), code.getMsg(), data);
        return apiResult;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(SystemCodeEnum.OK, data);
    }

    public static <T> Result<T> no(Code code) {
        return no(code, null);
    }

    public static <T> Result<T> no(Code code, T data) {
        return restResult(code, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
