package com.foo.config;

import com.foo.component.AspectComponent;
import com.foo.service.MathService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public MathService mathService() {
        return new MathService();
    }

    @Bean
    public AspectComponent aspectComponent() {
        return new AspectComponent();
    }
}
