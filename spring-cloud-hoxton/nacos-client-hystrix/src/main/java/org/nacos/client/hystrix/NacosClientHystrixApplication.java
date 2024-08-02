package org.nacos.client.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 开启feign支持
//@SpringBootApplication
//@EnableDiscoveryClient // 开启服务发现
//@EnableCircuitBreaker
@SpringCloudApplication
public class NacosClientHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientHystrixApplication.class);
    }
}
