package com.foobar.controller;


import com.foobar.common.code.Result;
import com.foobar.service.bar.BarApi;
import com.foobar.service.foo.FooApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController implements BarApi {


    public Result<String> info() {
        return Result.ok(this.getClass().toString() + "\tbar");
    }

    public Result<String> bar() {
        return Result.ok(Thread.currentThread().getName());
    }

    public Result<String> div() {
        int i = 1 / 0;
        return Result.ok("AA");
    }

    public Result<String> params(String name) {
        return Result.ok(name);
    }

}
