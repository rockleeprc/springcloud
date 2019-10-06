package com.foo.component;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

    /**
     *
     * @param context：Condition上下文环境
     * @param metadata：注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // IoC容器
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 类加载器
        ClassLoader classLoader = context.getClassLoader();
        // bean定义信息
        BeanDefinitionRegistry registry = context.getRegistry();
        // 环境信息
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");
        if(os!=null && os.contains("Mac")){
            return true;
        }

        return false;
    }
}
