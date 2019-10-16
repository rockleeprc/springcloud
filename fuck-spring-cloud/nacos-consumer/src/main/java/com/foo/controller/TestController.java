package com.foo.controller;

import com.foo.remote.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    private final RestTemplate restTemplate;

    @Autowired
    private RemoteClient remoteClient;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://nacos-provider/echo/" + str, String.class);
    }

    @RequestMapping(value = "/echo2/{str}", method = RequestMethod.GET)
    public String echo2(@PathVariable String str) {
        return remoteClient.echo2(str);
    }

    @RequestMapping(value = "/div", method = RequestMethod.GET)
    public Integer div(@RequestParam("i") Integer i, @RequestParam("j") Integer j) {
        Map<String, Integer> map = new HashMap<>();
        map.put("i", i);
        map.put("j", j);
        return restTemplate.getForObject("http://nacos-provider/div?i=" + i + "&j=" + j, Integer.class);
    }

    @RequestMapping(value = "/div2", method = RequestMethod.GET)
    public Integer div2(@RequestParam("i") Integer i, @RequestParam("j") Integer j) {
        return remoteClient.div2(i, j);
    }


}
