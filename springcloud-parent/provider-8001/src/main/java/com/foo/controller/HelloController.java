package com.foo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public String info() {
        return "provider-8001";
    }
}