package org.foo.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.foo.service.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FooController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/foo/info")
    public String info() {
        LocalDateTime now = LocalDateTime.now();
        log.info("request {}, port {}", now, port);
        return "now:" + now + " port:" + port;
    }

    @GetMapping("/foo/param1")
    public String getParam(String name, Integer age) {
        log.info("getParam");
        return name + "|" + age + "|" + port;
    }

    @PostMapping("/foo/param2")
    public String postParam(String name, Integer age) {
        log.info("postParam");
        return name + "|" + age + "|" + port;
    }

    @PostMapping("/foo/param3")
    public Person postParamEntity(@RequestBody/*将json数据映射为对象*/ Person person) {
        log.info("postParamEntity");
        return person;
    }

    @GetMapping("/foo/delay")
    public String delay() {
        log.info("delay");
        LocalDateTime now = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return now + "|" + port;
    }

    @HystrixCommand(fallbackMethod = "circuitBreakerFallBack")
    @RequestMapping("/foo/circuitBreaker")
    public String circuitBreaker(Integer i) {
        log.info("circuitBreaker");
        if (i < 0) {
            throw new RuntimeException("抛异常");
        }
        return i + "|" + port;
    }

    public String circuitBreakerFallBack(Integer i) {
        return "circuitBreakerFallBack " + "|" + i;
    }

    @RequestMapping("/foo/byzero")
    public String byzero(Integer i) {
        return 10 / i + "|" + port;
    }
}
