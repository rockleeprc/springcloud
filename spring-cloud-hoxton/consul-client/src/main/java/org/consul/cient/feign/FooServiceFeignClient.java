package org.consul.cient.feign;

import org.consul.cient.entity.Person;
import org.consul.cient.feign.fallback.FooServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "foo-service", fallback = FooServiceFallback.class)
public interface FooServiceFeignClient {

    // 与foo-service中的接口相同
    @GetMapping("/foo/info")
    String info();

    // 必须使用@RequestParam，且名称可foo-serice中的接口定义的参数名称一致
    @GetMapping("/foo/param1")
    String getParam(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/foo/param2")
    String postParam(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/foo/param3")
    Person postParamEntity(@RequestBody Person person);

    @GetMapping("/foo/delay")
    String delay();

    @GetMapping("/foo/fallback")
    String fallback(@RequestParam("i") Integer i);

    @RequestMapping("/foo/byzero")
    public String byzero(@RequestParam("i") Integer i);
}
