package com.example.demo.web;

import com.example.demo.po.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        Book b = new Book();
        System.out.println(b.getName());
        System.out.println(b.getAuthor());
        return "hello spring boot";
    }
}
