package com.foobar;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@MapperScan("com.foobar.mapper")
public class ProviderBarApplication {
    private final static Logger logger = LoggerFactory.getLogger(ProviderBarApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProviderBarApplication.class, args);
        logger.info("==========provider bar start");
        logger.debug("==========provider bar start");
    }
}
