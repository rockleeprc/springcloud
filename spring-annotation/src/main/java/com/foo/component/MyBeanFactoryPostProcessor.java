package com.foo.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("B postProcessBeanFactory");
        System.out.println("definition count:"+beanFactory.getBeanDefinitionCount());
        System.out.println("bd names:"+ Arrays.toString(beanFactory.getBeanDefinitionNames()));
    }
}
