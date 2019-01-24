package com.foobar.feign;

import com.foobar.feign.failback.BarFeignfallback;
import com.foobar.common.code.Result;
import com.foobar.pojo.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "provider-bar", fallback = BarFeignfallback.class)
public interface BarFeign {
    @RequestMapping("/bar/info")
    Result<String> info();

    @RequestMapping("/bar/div")
    Result<String> div();

    @RequestMapping("/bar/dely")
    Result<String> dely();

    @RequestMapping(value = "/bar/person",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Person> person(@RequestBody Person person);

}