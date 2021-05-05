package org.nacos.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("/test")
public class TestInfoController {
    @RequestMapping("/info")
    public String info() {
        return String.valueOf(LocalDateTime.now());
    }
}
