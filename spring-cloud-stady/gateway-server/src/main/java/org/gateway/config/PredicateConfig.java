package org.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

interface Parent {
    public static final Thread t = new Thread() {
        {
            System.out.println("parent in thread");
        }
    };
}

class Child implements Parent {
    public static String child = "I am child class";
}

//@Configuration
public class PredicateConfig {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
        Parent p = new Parent() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }


    /**
     * 获取UTC时间格式的参数，请求的时间在UTC时间之后匹配成功
     *
     * @return
     */
    @Bean
    public RouteLocator after(RouteLocatorBuilder builder) {
        ZonedDateTime dateTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        return builder
                .routes()
                .route("after_route", r -> r.after(dateTime).uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 请求的时间在UTC之前匹配成功
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator before(RouteLocatorBuilder builder) {
        ZonedDateTime dateTime = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        return builder
                .routes()
                .route("before_route", r -> r.after(dateTime).uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 请求时间在两个UTC时间之间匹配成功
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator between(RouteLocatorBuilder builder) {
        ZonedDateTime dateTime1 = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        ZonedDateTime dateTime2 = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        return builder
                .routes()
                .route("between_route", r -> r.between(dateTime1, dateTime2).uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 匹配断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator cookie(RouteLocatorBuilder builder) {
        ZonedDateTime dateTime1 = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        ZonedDateTime dateTime2 = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        return builder
                .routes()
                .route("cookie_route", r -> r.cookie("bmw", "m3").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 请求头断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator header(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("header_route", r -> r.header("X-reqeust-Id", "tiancai").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 访问的主机断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator host(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("host_route", r -> r.host("**.baidu.com:80").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * http 请求方法断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator method(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("method_route", r -> r.method("GET", "POST").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 请求参数断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator param(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("param_route", r -> r.query("name", "天才").uri("http://www.baidu.com"))
                .build();
    }

    /**
     * 请求的ip地址断言
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator ip(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("ip_route", r -> r.remoteAddr("127.0.0.1").uri("http://www.baidu.com"))
                .build();
    }


}
