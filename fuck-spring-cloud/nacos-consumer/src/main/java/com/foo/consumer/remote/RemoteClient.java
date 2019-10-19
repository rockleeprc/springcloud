package com.foo.consumer.remote;

import com.foo.consumer.config.FeignConfig;
import com.foo.consumer.remote.fallback.RemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nacos-provider", fallback = RemoteClientFallback.class, configuration = FeignConfig.class)
public interface RemoteClient {

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo2(@PathVariable("str") String str);

    @RequestMapping(value = "/div", method = RequestMethod.GET)
    public Integer div2(@RequestParam("i") Integer i, @RequestParam("j") Integer j);
}
/*
RemoteClientFallback可以写成内部类
@FeignClient：可以增加前缀
RemoteClientFallback：如果不标注@Component，需要单独在配置类中通过@Configuration+@Bean注入
 */
