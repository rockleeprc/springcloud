package com.foobar.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/foo")
public class FooController {


    @RequestMapping(path="/info")
    public String info(){
        return this.getClass().toString()+"\tfoo";
    }

    @RequestMapping(path="/div")
    public String div(){
        int i=1/0;
        return null;
    }
}
