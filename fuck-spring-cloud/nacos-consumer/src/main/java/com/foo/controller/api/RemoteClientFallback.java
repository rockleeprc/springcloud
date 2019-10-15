package com.foo.controller.api;

public class RemoteClientFallback implements RemoteClient {
    @Override
    public String echo2(String str) {
        return "RemoteClientHystrix";
    }

    @Override
    public Integer div2(Integer i, Integer j) {
        return -1;
    }
}
