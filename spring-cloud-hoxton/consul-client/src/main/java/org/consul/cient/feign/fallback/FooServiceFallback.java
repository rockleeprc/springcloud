package org.consul.cient.feign.fallback;

import org.consul.cient.entity.Person;
import org.consul.cient.feign.FooServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class FooServiceFallback implements FooServiceFeignClient {
    @Override
    public String info() {
        return null;
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
        return null;
    }

    @Override
    public String fallback(Integer i) {
        return null;
    }

    @Override
    public String byzero(Integer i) {
        return "byzero " + i;
    }
}
