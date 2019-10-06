package com.foo.config;

import com.foo.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/person.properties")// 加载配置文件
@Configuration
public class PropertyConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
