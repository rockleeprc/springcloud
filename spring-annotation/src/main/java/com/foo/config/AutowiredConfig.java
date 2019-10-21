package com.foo.config;

import com.foo.bean.Car;
import com.foo.bean.Person;
import com.foo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.foo.service"})
public class AutowiredConfig {

    @Bean
    public PersonService personService(){
        PersonService personService = new PersonService();
        personService.flag="B";
        return personService;
    }



    @Bean
    public Person person(Car car){
        /*
        Car 对象会自动装配进来，不用显示使用@Autowired
         */
        Person person = new Person();
        person.setCar(car);
        return person;
    }
    @Bean
    public Car car(){
        return new Car();
    }

}
/*

Spring @Autoware注入规则
    1、按照组件类型从IoC容器中查找
    2、多个相同类型组件时，将属性的名称作为id从IoC容器中查找
    3、当由多个类型相同的组件时，使用@Qualifier指定组件的id，也可以使用@Primary定义优先使用的bean
    4、自动装配对象默认是一定要实例话的，@Autoware(required=false)可以不用实例化
    5、方法、方法参数、属性
        @Bean+方法参数可以不显示添加@Autowired
        只有一个有参数构造器，@Autowired可以省略

JSR250 @Resource
    1、按照属性名称进行装配
    2、不支持rqueire=false、不支持@Primary

JSR330 @Inject
    1、倒入javax.inject依赖
    2、不支持rqueire=false

org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 */
