package com.example.controller;

import com.example.doamin.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/person/{id}")
    public Person person(@PathVariable() Long id, @RequestParam(required = false) String name) {
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        return p;
    }

    @GetMapping("/hello")
    public String hello() {
        return "spring Boot";
    }
}
