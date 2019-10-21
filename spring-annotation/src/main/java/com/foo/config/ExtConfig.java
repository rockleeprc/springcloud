package com.foo.config;

import com.foo.component.MyBeanDefinitionRegistryPostProcessor;
import com.foo.component.MyBeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import({MyBeanFactoryPostProcessor.class,MyBeanDefinitionRegistryPostProcessor.class})
public class ExtConfig {

}
