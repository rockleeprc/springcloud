package com.foo.provider.service;

import com.foo.provider.dao.EchoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoService {
    @Autowired
    private EchoDAO echoDAO;

    public String echo(String str) {
        return echoDAO.echo(str);
    }
}
