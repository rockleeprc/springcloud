package com.yuneke;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RepastPosHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepastPosHelperApplication.class, args);
    }
}
