package com.foobar.service.bar;

import com.foobar.common.code.Result;
import com.foobar.pojo.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/bar")
public interface BarApi {

    @RequestMapping(path = "/info")
    public Result<String> info();

    @RequestMapping(path = "/bar")
    Result<String> bar();

    @RequestMapping(path = "/div")
    Result<String> div();

    @RequestMapping(path = "/params")
    public Result<String> params(@RequestParam("name") String name);

    @RequestMapping(path = "/dely")
    public Result<String> dely();

    @RequestMapping("/thrw")
    public Result thrw();


    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public Result<Person> person(Person person);


}
