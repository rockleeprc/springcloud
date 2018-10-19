package com.foo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
//@EnableFeignClients(basePackages = {"com.foo"})
//@ComponentScan("com.foo")
public class DeptConsumerApp8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApp8002.class, args);
    }
}
