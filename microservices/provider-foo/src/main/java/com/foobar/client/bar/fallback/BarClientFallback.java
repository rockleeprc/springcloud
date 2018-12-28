package com.foobar.client.bar.fallback;

import com.foobar.client.bar.BarClient;
import com.foobar.common.code.Result;
import com.foobar.service.bar.BarApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component()
@RequestMapping("/barFallback")
public class BarClientFallback implements BarClient {
    @Override
    public Result<String> info() {
        return Result.err();
    }

    @Override
    public Result<String> bar() {
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
