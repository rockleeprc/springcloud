package com.foo.controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping(value = "/div")
    public Integer div(Integer i, Integer j) {
        return i / j;
    }
}
