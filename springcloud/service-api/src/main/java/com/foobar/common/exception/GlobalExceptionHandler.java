package com.foobar.common.exception;

import com.foobar.common.code.Result;
import com.foobar.common.code.SysCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception exception) {
        StringBuffer url = request.getRequestURL();
        return Result.err(SysCode.ERR, exception.getMessage(),url.toString());
    }

}
