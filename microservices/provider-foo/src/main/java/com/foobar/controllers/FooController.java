package com.foobar.controllers;


import com.foobar.common.code.Result;
import com.foobar.service.api.FooApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController implements FooApi {


    @Override
    public Result<String> info() {
        return Result.ok(this.getClass().toString() + "\tfoo");
    }

    @Override
    public Result<String> foo() {
        return Result.ok(Thread.currentThread().getName());
    }

    @Override
    public Result<String> div() {
        int i = 1 / 0;
        return Result.ok("AA");
    }

    @Override
    public Result<String> params(String name) {
        return Result.ok(name);
    }

}
