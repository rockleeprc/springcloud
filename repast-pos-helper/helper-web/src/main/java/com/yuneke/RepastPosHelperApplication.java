package com.yuneke;

import com.yuneke.common.EnvironmentHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class RepastPosHelperApplication {

    private static final Logger logger = LoggerFactory.getLogger(RepastPosHelperApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RepastPosHelperApplication.class, args);
        logger.info("repast-pos-helper Is Success!");
        logger.debug("Druid Monitor. http://"+EnvironmentHolder.getAddr()+":"+EnvironmentHolder.getPort() +"/druid/index.html");
        logger.debug("Sample Started. http://"+EnvironmentHolder.getAddr()+":" + EnvironmentHolder.getPort());
        System.err.println("---Don't Use Prod Profile In Your Local Envrionment---");
    }


}
