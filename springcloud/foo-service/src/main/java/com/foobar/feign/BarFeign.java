package com.foobar.feign;

import com.foobar.feign.failback.BarFeignfallback;
import com.foobar.common.code.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider-bar",fallback = BarFeignfallback.class)
public interface BarFeign {
    @RequestMapping(path = {"/bar/info"})
    Result<String> info();

    @RequestMapping(path = {"/bar/div"})
    Result<String> div();

    @RequestMapping(path={"/bar/dely"})
    Result<String> dely();

}