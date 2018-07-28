package com.exam.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/8.
 */
@Controller
public class ErrorController extends AbstractErrorController {

    Log log = LogFactory.getLog(ErrorController.class);
//    public ErrorController() {
//    }
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    @RequestMapping("/error2")
    public String getErrorPath() {
        System.out.println("error");
        return null;
    }

    @RequestMapping("/error")
    public String error(HttpServletResponse response) {
        System.out.println("error....");
        try {
            response.getWriter().write("error....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
