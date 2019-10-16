package com.foo.remote;

import com.foo.config.FeignConfig;
import com.foo.remote.fallback.RemoteClientFallback;
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
