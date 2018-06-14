package com.example.demo;

import com.example.demo.po.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class JacksonTest {
    private ObjectMapper mapper;

    @Before
    public void init(){
         mapper = new ObjectMapper();
    }

    @Test
    public void t3() throws IOException {
        String json = "{\"id\":10,\"name\":\"liy\"}";
        JsonNode node = mapper.readTree(json);
        int id = node.get("id").asInt();
        String name  = node.get("name").asText();
        System.out.printf("id:%s name:%s",id,name);
    }

    @Test
    public void t2() throws IOException {
        String json = "{\"id\":10,\"name\":\"liy\"}";
        Person p = mapper.readValue(json,Person.class);
        System.out.println(p);
    }

    @Test
    public void t1() throws JsonProcessingException {
        Person p = new Person();
        p.setName("liy");
        p.setId(10);
        String resutl = mapper.writeValueAsString(p);
        System.out.println(resutl);
    }

}
