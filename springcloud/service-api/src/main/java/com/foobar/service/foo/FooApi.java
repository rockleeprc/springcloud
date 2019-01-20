package com.foobar.service.foo;

import com.foobar.common.code.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/foo")
public interface FooApi {

    @RequestMapping("/invokeBar")
    public Result<String> invokeBar();

    @RequestMapping(path = "/info")
    public Result<String> info();

    @RequestMapping(path = "/foo")
    Result<String> foo();

    @RequestMapping(path = "/div")
    Result<String> div();

    @RequestMapping(path = "/params")
    public Result<String> params(@RequestParam("name") String name);
}
