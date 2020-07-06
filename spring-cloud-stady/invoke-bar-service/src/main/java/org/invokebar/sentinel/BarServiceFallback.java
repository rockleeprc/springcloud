package org.invokebar.sentinel;

import org.invokebar.feign.BarServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class BarServiceFallback implements BarServiceFeign {
    @Override
    public String echo(String message) {
        return "fallback method message: {" + message + "}";
    }

    @Override
    public String sleep(Integer timeout) {
        return "fallback method timeout: {" + timeout + "}";
    }
}
