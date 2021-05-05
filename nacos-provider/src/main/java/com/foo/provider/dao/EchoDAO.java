package com.foo.provider.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Repository;

@Repository
public class EchoDAO {

    @HystrixCommand(fallbackMethod = "fallback")
    public String echo(String str) {
        int i = 1 / 0;
        return str;
    }

    public String fallback(String str) {
        return "hystrix "+str;
    }
}
