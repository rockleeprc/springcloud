package com.yuneke;

import com.yuneke.common.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RepastPosHelperApplication {

    private static final Logger logger = LoggerFactory.getLogger(RepastPosHelperApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RepastPosHelperApplication.class, args);

        int port = ServiceInfo.getPort();
        logger.info("repast-pos-helper is success!");
        logger.debug("druid monitor. http://127.0.0.1:"+port +"/druid/index.html");
        logger.debug("sample started. http://127.0.0.1:" + port);
        System.err.println("---Don't Use Prod Profile In Your Local Envriment---");
    }


}
