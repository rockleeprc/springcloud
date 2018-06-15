package com.example.dao;

import com.example.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

    public void save(Person person) {
        System.out.println(person);
    }
}
