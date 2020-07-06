package org.gateway.config;

import org.gateway.filter.BarFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterConfig {
    @Bean
    public RouteLocator barFilter(RouteLocatorBuilder builer) {
        return builer.routes()
                .route(r -> r.path("/bar-service/bar/**") // 匹配路径
                        .filters(
                                f -> f.stripPrefix(1) // 剔除bar-service
                                .filter(new BarFilter()) // 应用过滤器
                                 )
                        .uri("lb://bar-service").id("bar_filter")
                ).build();
    }
}
