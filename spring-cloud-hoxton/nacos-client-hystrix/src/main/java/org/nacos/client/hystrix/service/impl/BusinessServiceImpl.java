package org.nacos.client.hystrix.service.impl;

import org.nacos.client.hystrix.service.IBusinessService;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements IBusinessService {
    public String doSomething() {
        int i = 1 / 0;
        return "IBusinessService";
    }
}
