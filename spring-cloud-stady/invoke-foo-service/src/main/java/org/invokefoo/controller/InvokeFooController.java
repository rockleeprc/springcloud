package org.invoke.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.invoke.feign.FooServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/invoke/foo")
public class InvokeFooController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FooServiceFeign fooServiceFeign;

    @RequestMapping("/sleep")
    public String sleep(Integer timeout){
        return fooServiceFeign.sleep(timeout);
    }

    //    @HystrixCommand(fallbackMethod = "echoFallback")
    @RequestMapping("/echo")
    public String echo(@RequestParam("info") String message) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("foo-service");
//        ServiceInstance serviceInstance = instances.get(0);
        // restTemplate 调用
//        String result = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/foo/echo?message={1}", String.class, message);
        // ribbon 调用
//        String result = restTemplate.getForObject("http://foo-service/foo/echo?message={1}", String.class, message);
        // feign 调用
        String result = fooServiceFeign.echo(message);
        return result;
    }

    private String echoFallback(String message) {
        return "fallback method {" + message + "}";
    }
}
