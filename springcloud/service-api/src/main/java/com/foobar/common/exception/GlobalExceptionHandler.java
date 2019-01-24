package com.foobar.common.exception;

import com.foobar.common.code.Result;
import com.foobar.common.code.SysCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception exception) {
        StringBuffer url = request.getRequestURL();
        String message = exception.getMessage();
        if (StringUtils.isEmpty(message)) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            String stackTrace = stringWriter.toString();
            message = stackTrace.substring(0,stackTrace.indexOf(")")+1);
        }
        return Result.err(SysCode.ERR, message, url.toString());
    }
}
