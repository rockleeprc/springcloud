package com.foobar.controller;


import com.foobar.common.code.Result;
import com.foobar.service.bar.BarApi;
import com.foobar.service.foo.FooApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController implements BarApi {


    @Override
    public Result<String> info() {
        return Result.ok(this.getClass().toString() + "\tbar");
    }

    @Override
    public Result<String> bar() {
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
