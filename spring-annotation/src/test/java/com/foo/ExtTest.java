package com.foo;

import com.foo.config.ExtConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ExtTest {

    @Test
    public void postProcessor(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        context.close();
    }
}
