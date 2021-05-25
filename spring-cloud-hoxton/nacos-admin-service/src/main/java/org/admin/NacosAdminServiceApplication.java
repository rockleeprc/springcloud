package org.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAdminServer // 开启AdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class NacosAdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosAdminServiceApplication.class, args);
    }
}
