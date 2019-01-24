package com.foobar.controller;


import com.foobar.common.code.Result;
import com.foobar.pojo.Person;
import com.foobar.service.bar.BarApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    public Result<String> dely() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok("sleep 10s");
    }

    @RequestMapping("/thrw")
    public Result thrw() {
        if (true) {
            throw new RuntimeException("自定义抛出");
        }
        return Result.ok();
    }

    public Result<Person> person(Person person) {
        Person p = new Person();
        p.setName("bar-" + person.getName());
        p.setAge(1 + person.getAge());
        return Result.ok(p);
    }

}
