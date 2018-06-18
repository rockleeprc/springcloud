package com.example.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggingController {
    private Log log = LogFactory.getLog(LoggingController.class);


    @GetMapping("print")
    public void log(){
        log.debug("debbug logging ...");
    }
}
