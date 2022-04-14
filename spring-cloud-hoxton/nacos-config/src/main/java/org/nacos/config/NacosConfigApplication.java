package org.nacos.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(NacosConfigApplication.class);
        while(true) {
            String user = context.getEnvironment().getProperty("user");
            String age = context.getEnvironment().getProperty("age");
            System.out.println(user + "-" + age);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
