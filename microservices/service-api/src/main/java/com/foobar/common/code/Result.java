package com.foobar.common.code;


import java.io.Serializable;
import java.util.Date;

public class Result<T> implements Serializable {

    private Integer status;//状态码
    private String exception;//异常信息
    private String message;//提示信息
    private String path;//请求路径
    private T data;//数据
    private Date timestamp;//请求时间


    private Result() {
        this.timestamp = new Date();
    }

    private Result(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

    private Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    private Result(Integer status, String message, String exception, String path) {
        this.status = status;
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.timestamp = new Date();
    }

    public static <T> Result<T> ok() {
        return new Result(SysCode.OK.getCode(), SysCode.OK.getMessage());
    }

    public static <T> Result<T> ok(T data) {
        return new Result(SysCode.OK.getCode(), SysCode.OK.getMessage(), data);
    }
    public static <T> Result<T> err() {
        return new Result(SysCode.ERR.getCode(), SysCode.ERR.getMessage());
    }

    public static <T> Result<T> err(T data) {
        return new Result(SysCode.ERR.getCode(), SysCode.ERR.getMessage(),data);
    }

    public static <T> Result<T> err(Code code, String exception, String path) {
        return new Result(code.getCode(), code.getMessage(), exception, path);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
