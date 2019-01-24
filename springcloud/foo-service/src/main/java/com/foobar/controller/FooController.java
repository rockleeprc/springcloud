package com.foobar.controller;

import com.foobar.feign.BarFeign;
import com.foobar.common.code.Result;
import com.foobar.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/foo")
public class FooController {

    @Autowired
    private BarFeign barFeign;

    @RequestMapping("/invokeInfo")
    public Result invokeInfo() {
        return barFeign.info();
    }

    @RequestMapping("/invokeDiv")
    public Result invokeDiv() {
        return barFeign.div();
    }

    @RequestMapping("/invokeDely")
    public Result invokeDely() {
        return barFeign.dely();
    }

    @RequestMapping("/thrw")
    public Result thrw() {
        if (true) {
            throw new RuntimeException("自定义抛出");
        }
        return Result.ok();
    }

    @RequestMapping(value = "/invokePerson")
    public Result<Person> invokePerson(Person person) {
        System.out.println(person);
        return barFeign.person(person);
    }
}