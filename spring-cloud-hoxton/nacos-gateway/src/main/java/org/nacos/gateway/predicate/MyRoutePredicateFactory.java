package org.nacos.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    /**
     * DateTime key.
     */
    public static final String MY_PARAM = "myParam";

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(MY_PARAM);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                System.out.println(config.getMyParam());
                // 配置必须等于liyan
                return "liyan".equals(config.getMyParam());
            }

            @Override
            public String toString() {
                return config.getMyParam();
            }
        };
    }

    public static class Config {

        @NotNull
        private String myParam;

        public String getMyParam() {
            return myParam;
        }

        public void setMyParam(String myParam) {
            this.myParam = myParam;
        }
    }

}