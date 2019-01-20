package com.foo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZuulServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulServerApplication.class).run(args);
    }

}