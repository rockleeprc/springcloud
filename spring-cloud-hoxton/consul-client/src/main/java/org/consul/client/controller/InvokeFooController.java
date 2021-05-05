package org.consul.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.consul.client.entity.Person;
import org.consul.client.feign.FooServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class InvokeFooController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FooServiceFeignClient fooServiceFeignClient;

    /**
     * RestTemplate直接调用foo-service
     *
     * @return
     */
    @GetMapping("/invoke/foo/info")
    public String invokeFooForRestTemplateInfo() {
        log.info("invokeFooForRestTemplateInfo");
        // http://127.0.0.1:9900/foo/info
        RestTemplate restTemplate = new RestTemplate();
        // 参数：url 返回值类型
        String result = restTemplate.getForObject("http://127.0.0.1:9900/foo/info", String.class);
        log.info("invokeFoinvokeFooForRestTemplateInfo1oInfo1 {}", result);
        return result;
    }


    /**
     * Ribbon:discovery client
     *
     * @return
     */
    @GetMapping("/invoke/foo/info1")
    public List<ServiceInstance> invokeFooForRibbonInfo1() {
        log.info("invokeFooForRibbonInfo1");
        // 通过注册中心获取所有服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("foo-service");// 服务名称
        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info("host {},port {},instance-id {}", serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getInstanceId());
        }
        // TODO 自己写调用逻辑，手动负载均衡
        return serviceInstances;
    }

    /**
     * Ribbon:LoadBalancerClient
     *
     * @return
     */
    @GetMapping("/invoke/foo/info2")
    public String invokeFooForRibbonInfo2() {
        log.info("invokeFooForRibbonInfo2");
        // 默认轮询策略，选择一台服务
        ServiceInstance serviceInstance = loadBalancerClient.choose("foo-service");
        log.info("choose:{}", serviceInstance);
        // 手动拼接url
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/foo/info";
        log.info("url:{}", url);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    /**
     * Ribbon:@LoadBalanced
     *
     * @return
     */
    @GetMapping("/invoke/foo/info3")
    public String invokeFooForRibbonInfo3() {
        log.info("invokeFooForRibbonInfo3");
        // url 只需要写服务名称，负载均衡由rest+ribbon自动实现
        String result = restTemplate.getForObject("http://foo-service/foo/info", String.class);
        return result;
    }

    @GetMapping("/invoke/foo/info4")
    public String invokeFooForOpenFeignInfo() {
        log.info("invokeFooForOpenFeignInfo");
        return fooServiceFeignClient.info();
    }

    @GetMapping("/invoke/foo/param1")
    public String invokeFooParam1(String name, Integer age) {
        return fooServiceFeignClient.getParam(name, age);
    }

    @PostMapping("/invoke/foo/param2")
    public String invokeFooParam2(String name, Integer age) {
        return fooServiceFeignClient.postParam(name, age);
    }

    @PostMapping("/invoke/foo/param3")
    public Person invokeFooParam2(Person person) {
        return fooServiceFeignClient.postParamEntity(person);
    }

    @GetMapping("/invoke/foo/delay")
    public String invokeFooDelay() {
        return fooServiceFeignClient.delay();
    }

    @GetMapping("/invoke/foo/byzero")
    public String byzero(Integer i) {
        return fooServiceFeignClient.byzero(i);
    }
}
