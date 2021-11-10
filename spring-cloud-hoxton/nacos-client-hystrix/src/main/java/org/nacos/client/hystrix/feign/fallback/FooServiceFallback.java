package org.nacos.client.hystrix.feign.fallback;

import org.nacos.client.hystrix.entity.Person;
import org.nacos.client.hystrix.feign.FooServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class FooServiceFallback implements FooServiceFeignClient {
    @Override
    public String info() {
        return "FooServiceFallback.info";
    }

    @Override
    public String getParam(String name, Integer age) {
        return null;
    }

    @Override
    public String postParam(String name, Integer age) {
        return null;
    }

    @Override
    public Person postParamEntity(Person person) {
        return null;
    }

    @Override
    public String delay() {
        return "FooServiceFallback.delay()";
    }

    @Override
    public String fallback(Integer i) {
        return null;
    }

    @Override
    public String byzero(Integer i) {
        return "byzero    " + i;
    }
}
