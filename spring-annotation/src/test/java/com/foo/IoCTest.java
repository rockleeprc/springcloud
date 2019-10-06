package com.foo;

import com.foo.bean.Car;
import com.foo.bean.Person;
import com.foo.config.*;
import com.foo.service.PersonService;
import org.junit.Test;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

public class IoCTest {


    @Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        Person person = (Person) applicationContext.getBean("personFactoryBean");
        ImportConfig.PersonFactoryBean factoryBean = (ImportConfig.PersonFactoryBean) applicationContext.getBean("&personFactoryBean");
        System.out.println(person);
        System.out.println(factoryBean.getClass());
    }

    @Test
    public void test2(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

    @Test
    public void test3(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("os.name"));
    }

    @Test
    public void test4(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(ImportConfig.class);
        String[] beanDefinitionNames = application.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

    @Test
    public void test5(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(LifeConfig.class);
        Car car = (Car) application.getBean("car");
        application.close();
    }
    @Test
    public void test6(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(PropertyConfig.class);
        Person per = (Person) application.getBean("person");
        System.out.println(per);

        // 通过evn获取properties中的内容
        ConfigurableEnvironment environment = application.getEnvironment();
        System.out.println(environment.getProperty("person.name"));
        System.out.println(environment.getProperty("person.address"));
        application.close();
    }

    @Test
    public void testAutwaireConfig(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        Person person = application.getBean(Person.class);
        Car car = application.getBean(Car.class);
        System.out.println(person.getCar());
        System.out.println(car);

        PersonService personService = application.getBean(PersonService.class);
        System.out.println(personService.flag);
    }

    @Test
    public void testProfileConfig(){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext();
        application.getEnvironment().setActiveProfiles("test");
        application.register(ProfileConfig.class);
        application.refresh();

        Arrays.stream(application.getBeanDefinitionNames()).forEach(System.out::println);

        application.close();

    }

}
