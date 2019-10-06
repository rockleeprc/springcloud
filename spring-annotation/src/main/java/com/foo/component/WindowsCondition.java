package com.foo.component;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");

        BeanDefinitionRegistry registry = context.getRegistry();
        if(registry.containsBeanDefinition("per")){
            return true;
        }

        if(os!=null && os.contains("Windows")){
            return true;
        }
        return false;
    }
}
