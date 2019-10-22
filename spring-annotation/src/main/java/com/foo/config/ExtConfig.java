package com.foo.config;

import com.foo.component.MyBeanDefinitionRegistryPostProcessor;
import com.foo.component.MyBeanFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyBeanFactoryPostProcessor.class,MyBeanDefinitionRegistryPostProcessor.class})
public class ExtConfig {

}
/*
BeanDefinitionRegistryPostProcessor先于BeanFactoryPostProcessor执行
 */
