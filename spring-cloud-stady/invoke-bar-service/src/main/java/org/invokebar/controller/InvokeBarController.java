package org.invokebar.controller;

import org.invokebar.feign.BarServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/invoke/bar")
public class InvokeBarController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BarServiceFeign barServiceFeign;

    @RequestMapping("/sleep")
    public String sleep(Integer timeout) {
        return barServiceFeign.sleep(timeout);
    }

    // 定义资源 value 不设置是当前方法的全限定名
//    @SentinelResource(blockHandler = "echoBlockHandler",fallback = "echoFallback")
    @RequestMapping("/echo")
    public String echo(@RequestParam("info") String message) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("foo-service");
//        ServiceInstance serviceInstance = instances.get(0);
        // restTemplate 调用
//        String result = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/foo/echo?message={1}", String.class, message);
        // ribbon 调用
//        String result = restTemplate.getForObject("http://bar-service/bar/echo?message={1}", String.class, message);
        // feign 调用
        String result = barServiceFeign.echo(message);
        return result;
    }
}
