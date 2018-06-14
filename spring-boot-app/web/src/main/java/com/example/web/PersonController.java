package com.example.web;


import com.example.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/person")
    public void save(Person person){
        System.out.println(person);
    }
}
