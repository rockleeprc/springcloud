package com.foo.config;

import com.foo.bean.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

@PropertySource("classpath:/person.properties")// 加载配置文件
@Configuration
public class PropertyConfig implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Bean
    public Person person(@Value("${person.name}") String name) {
        System.out.println(name);
        System.out.println(stringValueResolver.resolveStringValue("${person.address}"));
        return new Person();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
