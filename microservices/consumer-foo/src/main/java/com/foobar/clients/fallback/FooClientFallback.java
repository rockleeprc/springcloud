package com.foobar.clients.fallback;

import com.foobar.clients.FooClient;
import org.springframework.stereotype.Component;

@Component
public class FooClientFallback implements FooClient {
    @Override
    public String foo() {
        return "error";
    }

    @Override
    public String div() {
        return "error";
    }
}
