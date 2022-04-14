package org.consul.foo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker // 开启断路器
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulFooServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulFooServiceApplication.class);
    }
}
