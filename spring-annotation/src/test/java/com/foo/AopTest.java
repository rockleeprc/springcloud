package com.foo;

import com.foo.config.AopConfig;
import com.foo.service.MathService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    @Test
    public void testAop(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        MathService mathService = context.getBean(MathService.class);
        System.out.println(mathService.div(10, 1));

    }
}
