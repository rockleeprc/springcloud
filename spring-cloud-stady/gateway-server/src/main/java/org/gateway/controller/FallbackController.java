package org.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String fallback() {
        System.out.println("hystrix fallback");
        return "hystrix fallback";
    }
}
