package org.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient //和@EnableEurekaClient 作用一样，开启客户端注册功能也可以不写
public class BarApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarApplication.class, args);
    }
}
