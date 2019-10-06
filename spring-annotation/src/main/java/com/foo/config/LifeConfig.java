package com.foo.config;

import com.foo.bean.Car;
import com.foo.component.MyBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeConfig {

    @Bean(initMethod = "initMthod", destroyMethod = "destroyMethod")
    public Car car() {
        return new Car();
    }

    @Bean
    public BeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
