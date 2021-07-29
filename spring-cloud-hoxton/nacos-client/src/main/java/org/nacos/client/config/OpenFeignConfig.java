package org.nacos.client.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用@Configuration表示全局配置
 */
//@Configuration
public class OpenFeignConfig {

    @Bean
    public Logger.Level openFeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
