package org.invoke.hystrix;

import org.invoke.feign.FooServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class FooServiceFallback implements FooServiceFeign {
    @Override
    public String echo(String message) {
        return "fallback method message: {" + message + "}";
    }

    @Override
    public String sleep(Integer timeout) {
        return "fallback method timeout: {" + timeout + "}";
    }
}
