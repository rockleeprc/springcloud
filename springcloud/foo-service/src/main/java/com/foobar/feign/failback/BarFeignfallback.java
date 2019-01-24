package com.foobar.feign.failback;

import com.foobar.feign.BarFeign;
import com.foobar.common.code.Result;
import org.springframework.stereotype.Component;

@Component
public class BarFeignfallback implements BarFeign {

    @Override
    public Result<String> info() {
        return Result.err("info() fallback");
    }

    @Override
    public Result div() {
        return Result.err("div() fallback");
    }

    @Override
    public Result<String> dely() {
        return Result.err("dely() fallback");
    }
}