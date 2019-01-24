package com.foobar.feign.failback;

import com.foobar.feign.BarFeign;
import com.foobar.common.code.Result;
import com.foobar.pojo.Person;
import org.springframework.stereotype.Component;

@Component
public class BarFeignfallback implements BarFeign {

    @Override
    public Result<String> info() {
        return Result.err("info() fallback");
    }

    @Override
    public Result div() {
        return Result.err("div() fallback");
    }

    @Override
    public Result<String> dely() {
        return Result.err("dely() fallback");
    }

    @Override
    public Result<Person> person(Person person) {
        return Result.ok(new Person("fallback", 99, null));
    }
}