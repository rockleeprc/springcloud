package com.example.eurekaserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer//启动一个服务注册中心
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("go");
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}