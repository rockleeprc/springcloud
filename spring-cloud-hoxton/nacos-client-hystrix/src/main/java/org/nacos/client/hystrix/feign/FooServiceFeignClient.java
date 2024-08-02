package org.nacos.client.hystrix.feign;

import org.nacos.client.hystrix.entity.Person;
import org.nacos.client.hystrix.feign.fallback.FooServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * name：服务名称
 * path：服务提供方controller中@RequestMapping("/foo")的名称
 * configuration：使用配置类
 */
@FeignClient(name = "nacos-foo-service", path = "/foo", fallback = FooServiceFallback.class)
public interface FooServiceFeignClient {

    // 与nacos-foo-service中的controller方法相同
    @GetMapping("/foo/info")
    String info();

    // 必须使用@RequestParam，且名称和foo-service中的接口定义的参数名称一致
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
