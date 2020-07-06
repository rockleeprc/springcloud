package org.foo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/foo")
public class FooController {

    @Value("${server.port}")
    private int serverPort;

    @RequestMapping("/echo")
    public String echo(String message) {
        System.out.println("FooController.echo " + System.currentTimeMillis());
        if ("exception".equals(message)) {
            throw new RuntimeException("echo Exception");
        }
        return "foo-service echo {" + message + "} from serverPort=" + serverPort;
    }

    @RequestMapping("/sleep")
    public String sleep(Integer timeout) throws InterruptedException {
        System.out.println("FooController.sleep {"+timeout+"} " + System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(timeout);
        return "timeout {" + timeout + "}";
    }
}
