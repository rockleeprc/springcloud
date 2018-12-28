package com.foobar.service.impl;

import com.foobar.client.bar.BarClient;
import com.foobar.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServicImpl implements BusinessService {

    @Autowired
    private BarClient barClient;

    @Override
    public String invokBar() {
        return barClient.info().getData();
    }
}
