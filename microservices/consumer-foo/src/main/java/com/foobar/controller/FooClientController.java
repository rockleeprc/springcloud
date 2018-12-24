package com.foobar.controller;

import com.foobar.common.code.Result;
import com.foobar.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client")
public class FooClientController {
    @Autowired
    private BusinessService fooBusiness;

    @RequestMapping("/foo")
    public Result<String> foo() {
        return Result.ok(fooBusiness.foo());
    }

    @RequestMapping(path = "/info")
    public Result<String> info() {
        return Result.ok(this.getClass().toString() + "\t" + fooBusiness.foo());
    }

    @RequestMapping(path = "/div")
    public Result<String> div() {
        return Result.ok(fooBusiness.div());
    }

    @RequestMapping(path = "/params")
    public Result<String> parmas(@RequestParam("name") String name) {
        return Result.ok(fooBusiness.params(name));
    }
}
