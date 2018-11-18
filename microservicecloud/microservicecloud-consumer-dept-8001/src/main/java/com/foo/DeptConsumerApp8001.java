package com.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeptConsumerApp8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApp8001.class, args);
    }
}
