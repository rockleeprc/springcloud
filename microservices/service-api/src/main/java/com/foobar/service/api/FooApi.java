package com.foobar.service.api;

import com.foobar.common.code.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/foo")
public interface FooApi {

    @RequestMapping(path = "/info")
    public Result<String> info();

    @RequestMapping(path = "/foo")
    Result<String> foo();

    @RequestMapping(path = "/div")
    Result<String> div();

    @RequestMapping(path = "/params")
    public Result<String> params(@RequestParam("name") String name);
}
