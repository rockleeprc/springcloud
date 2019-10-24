package com.foo.provider.controller;

import org.springframework.beans.BeansException;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class EchoController implements ApplicationListener<WebServerInitializedEvent> {

    private WebServerInitializedEvent webServerInitializedEvent;

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        System.out.println("port=" + webServerInitializedEvent.getWebServer().getPort());
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping(value = "/div")
    public Integer div(Integer i, Integer j) {
        return i / j;
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.webServerInitializedEvent = webServerInitializedEvent;
    }
}
