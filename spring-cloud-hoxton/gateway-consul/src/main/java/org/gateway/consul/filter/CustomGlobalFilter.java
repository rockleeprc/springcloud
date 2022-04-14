package org.gateway.consul.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("filter before");
        Mono<Void> result = chain.filter(exchange);
        System.out.println("after before");
        return result; // 允许访问
//        return exchange.getResponse().setComplete();// 拒绝访问
    }

    @Override
    public int getOrder() {
        // 越小 越优先
        return 0;
    }
}
