package com.foobar.facade;

import com.foobar.client.FooClient;
import com.foobar.common.code.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooFacade {

    @Autowired
    private FooClient fooClient;

    public String params(String name) {
        return fooClient.params(name).getData();
    }

    public String foo() {
        return fooClient.foo().getData();
    }

    public String info() {
        return this.getClass().toString() + "\t" + fooClient.foo().getData();
    }

    public String div() {
        Result<String> result = fooClient.div();
        return result.toString();
    }
}
