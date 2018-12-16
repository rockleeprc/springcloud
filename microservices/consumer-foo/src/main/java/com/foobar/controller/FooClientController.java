package com.foobar.controller;

import com.foobar.clients.FooClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client")
public class FooClientController {

    @Autowired
    private FooClient fooClient;

    @RequestMapping(path="/info")
    public String info(){
        return this.getClass().toString()+"\t"+fooClient.foo();
    }

    @RequestMapping(path="/div")
    public String div(){
        return fooClient.div();
    }
}
