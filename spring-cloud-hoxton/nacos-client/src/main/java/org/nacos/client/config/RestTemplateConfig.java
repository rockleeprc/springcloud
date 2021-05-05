package org.nacos.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 带有Ribbon负载均衡的RestTemplate
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}