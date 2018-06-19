package com.example.web;


import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public void save(Person person) {
        personService.save(person);
    }

    @GetMapping("/json")
    @ResponseBody
    public Map<String,Date> json(){
        Map<String,Date> map = new HashMap<>();
         map.put("date",new Date());
         return map;
    }
}
