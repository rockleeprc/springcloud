package com.foobar.clients;

import com.foobar.clients.fallback.FooClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="PROVIDER-FOO",fallback = FooClientFallback.class)
public interface FooClient {

    @RequestMapping(path="/foo/info")
    String foo();

    @RequestMapping(path="/foo/div")
    String div();
}
