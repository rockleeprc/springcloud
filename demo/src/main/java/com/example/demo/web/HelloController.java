package com.example.demo.web;

import com.example.demo.po.Book;
import com.example.demo.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient clinet;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        ServiceInstance instance = clinet.getLocalServiceInstance();
        System.out.println(instance.getHost());
        System.out.println(instance.getServiceId());
        return "hello spring boot";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        ServiceInstance instance = clinet.getLocalServiceInstance();
        System.out.println(instance.getHost());
        System.out.println(instance.getServiceId());
        return "hello spring info";
    }

    @GetMapping(value = "/person/{id}")
    public Person person(@PathVariable() int id, @RequestParam(required = false) String name) {
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        return p;
    }


}
