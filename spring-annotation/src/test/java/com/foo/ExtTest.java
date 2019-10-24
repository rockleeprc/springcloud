package com.foo;

import com.foo.component.MyBeanDefinitionRegistryPostProcessor;
import com.foo.config.ExtConfig;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringValueResolver;

import java.beans.PropertyEditor;
import java.lang.annotation.Annotation;
import java.security.AccessControlContext;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExtTest {

    @Test
    public void postProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);
        String[] beanDefinitionRegistry = context.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class);
        String[] beanfactory = context.getBeanNamesForType(BeanFactoryPostProcessor.class);
        System.out.println(Arrays.toString(beanDefinitionRegistry));
        System.out.println(Arrays.toString(beanfactory));
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        System.out.println(context instanceof BeanDefinitionRegistry);

        ConfigurableListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        System.out.println(listableBeanFactory instanceof BeanDefinitionRegistry);

        MyBeanDefinitionRegistryPostProcessor bean = context.getBean(MyBeanDefinitionRegistryPostProcessor.class);
        System.out.println(bean);

        context.close();
    }
}
