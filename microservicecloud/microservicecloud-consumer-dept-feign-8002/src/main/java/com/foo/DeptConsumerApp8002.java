package com.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableFeignClients(basePackages = {"com.foo"})
//@ComponentScan("com.foo")
public class DeptConsumerApp8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApp8002.class, args);
    }
}
