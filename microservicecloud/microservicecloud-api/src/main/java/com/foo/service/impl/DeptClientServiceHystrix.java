package com.foo.service.impl;

import com.foo.entities.Dept;
import com.foo.service.DeptClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DeptClientServiceHystrix implements DeptClientService {

    @Override
    public Dept get(Long id) {
        return new Dept("熔断");
    }

    @Override
    public List<Dept> list() {
        return new ArrayList<>(Arrays.asList(new Dept("熔断")));
    }

    @Override
    public boolean add(Dept dept) {
        return true;
    }

    @Override
    public boolean zero() {
        System.out.println("*******************");
        return true;
    }
}
