package com.foo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Car implements InitializingBean, DisposableBean {

    public Car(){
        System.out.println("Constructor Car");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void preDestory(){
        System.out.println("@PreDestroy");
    }

    public void initMthod() {
        System.out.println("init-method");
    }

    public void destroyMethod() {
        System.out.println("destroy-method");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }
}
