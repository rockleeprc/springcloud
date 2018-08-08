package com.example;

import com.example.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootProfileApplicationTests {

    @Autowired
    private Person person;
    @Value("${person.lastName}")
    private String lastName;
    @Value("${person.dog.name}")
    private String name;

    @Test
    public void person() {
        System.out.println(person);
        System.out.println(lastName);
        System.out.println(name);
    }

    @Test
    public void contextLoads() {
    }

}
