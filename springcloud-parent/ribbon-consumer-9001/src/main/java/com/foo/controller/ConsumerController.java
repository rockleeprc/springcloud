package com.foo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path="/info",method = RequestMethod.GET)
    public String info() {
        return restTemplate.getForEntity("http://PROVIDER/hello/info", String.class).getBody();
    }

    @RequestMapping(path = "/foo")
    public boolean foo(){
        return true;
    }
}
