package com.foo.service;

import java.util.List;

import com.foo.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "MICROSERVICECLOUD-DEPT")
public interface DeptClientService {
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);
}
