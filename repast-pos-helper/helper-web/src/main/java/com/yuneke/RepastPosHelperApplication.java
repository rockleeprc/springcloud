package com.yuneke;

import com.yuneke.common.ServiceInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RepastPosHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepastPosHelperApplication.class, args);
        System.err.println("repast-pos-helper is success!");
        System.err.println("druid monitor. http://127.0.0.1:"+ ServiceInfo.getPort()+"/druid/index.html");
        System.err.println("sample started. http://127.0.0.1:" + ServiceInfo.getPort());
    }
}
