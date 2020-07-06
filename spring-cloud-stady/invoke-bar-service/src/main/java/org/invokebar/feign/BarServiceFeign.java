package org.invokebar.feign;

import org.invokebar.sentinel.BarServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bar-service", fallback = BarServiceFallback.class)
public interface BarServiceFeign {

    @RequestMapping("/bar/echo")
    String echo(@RequestParam("message") String message);

    @RequestMapping("/bar/sleep")
    String sleep(@RequestParam("timeout") Integer timeout);

}
