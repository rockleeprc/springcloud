package org.invoke.feign;

import org.invoke.hystrix.FooServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(value = "foo-service", fallback = FooServiceFallback.class)
public interface FooServiceFeign {

    @RequestMapping("/foo/echo")
    String echo(@RequestParam("message") String message);

    @RequestMapping("/foo/sleep")
    String sleep(@RequestParam("timeout") Integer timeout);

}
