package com.foobar.client.fallback;

import com.foobar.client.FooClient;
import com.foobar.common.code.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component()
@RequestMapping("/fooFallback")
public class FooClientFallback implements FooClient {
    @Override
    public Result<String> info() {
        return Result.err();
    }

    @Override
    public Result<String> foo() {
        return Result.err();
    }

    @Override
    public Result<String> div() {
        return Result.err("fallback");
    }

    @Override
    public Result<String> params(String name) {
        return Result.err();
    }
}
