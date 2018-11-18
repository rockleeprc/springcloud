package com.foo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod ="infoFallback" )
    public String info() {
        return restTemplate.getForEntity("http://PROVIDER/hello/info", String.class).getBody();
    }


    public String infoFallback(){
        return "hystrix";
    }
    @RequestMapping(path = "/foo")
    public boolean foo(){
        return true;
    }
}
