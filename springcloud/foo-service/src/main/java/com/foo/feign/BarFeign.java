package com.foo.feign;

import com.foobar.common.code.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider-bar")
@RequestMapping("/bar")
public interface BarFeign {
    @RequestMapping(path = {"/info"})
    Result<String> info();
}