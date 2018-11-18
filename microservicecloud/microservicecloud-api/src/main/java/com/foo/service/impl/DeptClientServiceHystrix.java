package com.foo.service.impl;

import com.foo.entities.Dept;
import com.foo.service.DeptClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DeptClientServiceHystrix implements DeptClientService {

    @Override
    public Dept get(Long id) {
        log.info("get() 熔断接口");
        return new Dept("熔断");

    }

    @Override
    public List<Dept> list() {
        log.info("list() 熔断接口");
        return new ArrayList<>(Arrays.asList(new Dept("熔断")));
    }

    @Override
    public boolean add(Dept dept) {
        return true;
    }

    @Override
    public boolean zero() {
        log.info("list() 熔断接口");
        return true;
    }
}
