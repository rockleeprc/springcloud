package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Controller必须在该类所在包的子包下，才能被扫描到，同及包都不可以
 */
@SpringBootApplication
public class BootStrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}
