package com.foo.controller;

import com.foo.feign.BarFeign;
import com.foobar.common.code.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class FooController {

    @Autowired
    private BarFeign barFeign;

    @RequestMapping("/invoke")
    public Result invoke() {
        return barFeign.info();
    }
}