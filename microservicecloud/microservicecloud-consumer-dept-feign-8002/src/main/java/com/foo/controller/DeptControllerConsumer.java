package com.foo.controller;

import java.util.List;

import com.foo.entities.Dept;
import com.foo.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/consumer")
public class DeptControllerConsumer {
    @Autowired
    private DeptClientService deptClientService;


    @RequestMapping(value = "/dept/add")
    public boolean add(Dept dept) {
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/dept/list")
    public List<Dept> list() {
        System.out.println("--------------"+deptClientService);
        return deptClientService.list();
    }

}
