package com.foobar.common.code;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 问题：浏览器和客户端都会返回json数据
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
//    @RequestBody
    public Result handleException(HttpServletRequest request, Exception exception) {
        StringBuffer url = request.getRequestURL();
        return Result.err(SysCode.ERR, exception.getMessage(),url.toString());
    }

//    /**
//     * 自适应，浏览器返回5xx.html，客户端返回json
//     *
//     * @param exception
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception exception, HttpServletRequest request) {
//        request.setAttribute("javax.servlet.error.status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return "forward:/error";
//    }
}
