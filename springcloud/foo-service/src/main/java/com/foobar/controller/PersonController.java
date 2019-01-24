package com.foobar.controller;

import com.foobar.pojo.Person;
import com.foobar.common.code.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

    @RequestMapping("/ping1")
    public Result<Person> ping1(Person person) {
        return Result.ok(person);
    }

    @RequestMapping("/ping2")
    public Result ping2(@Size(min = 1, max = 5, message = "length must between 1 and 5") String name,
                        @RequestParam("age")
                        @Min(value = 1, message = "age>=1")
                        @Max(value = 99, message = "age <=99") Integer age) {
        return Result.ok(name + age);
    }
}