package com.example.web;

import com.example.config.EnvConfig;
import com.example.config.PropertyConfig;
import com.example.config.SpringDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigController {
    @Autowired
    private EnvConfig env;

    @Autowired
    private SpringDatasource springDatasource;

    @Autowired
    private PropertyConfig propertyConfig;

    /**
     * 通过Environment读取
     *
     * @return
     */
    @RequestMapping("/serverPort")
    public int getServicePort() {
        return env.getServerPort();
    }

    @RequestMapping("/home")
    public String home() {
        return env.getJavaHome();
    }

    /**
     * 通关过@ConfigurationProperties获取一组配置
     * @return
     */
    @RequestMapping("/ds")
    public String ds() {
        System.out.println(env.getDatasourceUrl());
        return springDatasource.toString();
    }
    /**
     * 通关过@ConfigurationProperties获取一组配置
     * @return
     */
    @RequestMapping("/pc")
    public String pc() {
        return propertyConfig.toString();
    }

    /**
     * 通过@Value注入
     *
     * @param port
     * @return
     */
    @RequestMapping("/port")
    public String port(@Value("${server.port}") int port) {
        return "port:" + port;
    }

}
