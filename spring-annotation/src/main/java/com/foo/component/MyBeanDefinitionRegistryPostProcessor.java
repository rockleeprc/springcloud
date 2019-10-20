package com.foo.component;

import com.foo.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

import java.util.Arrays;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry");
        System.out.println("definition count:" + registry.getBeanDefinitionCount());
        System.out.println("bd names:"+ Arrays.toString(registry.getBeanDefinitionNames()));
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("blue", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("A postProcessBeanFactory");
        System.out.println("definition count:" + beanFactory.getBeanDefinitionCount());
        System.out.println("bd names:"+ Arrays.toString(beanFactory.getBeanDefinitionNames()));
    }
}
