package com.foo.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    @Value("张三")// 直接赋值
    private String name;
    @Value("#{10+10}")// SpringEL表达式
    private Integer age;
    @Value("${person.address}")// 配置文件赋值
    private String address;

    private Car car;

    public Person() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
