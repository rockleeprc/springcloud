package com.foo.config;

import com.foo.bean.Car;
import com.foo.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Profile("dev")
    @Bean
    public Car car(){
        return new Car();
    }

    @Profile("test")
    @Bean
    public Person person(){
         return new Person();
    }
}
