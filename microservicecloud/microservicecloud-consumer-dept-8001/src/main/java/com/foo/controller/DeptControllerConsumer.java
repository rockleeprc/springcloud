package com.foo.controller;

import java.util.List;

import com.foo.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/consumer")
public class DeptControllerConsumer
{

//	private static final String REST_URL_PREFIX = "http://localhost:9001"; //直接访问服务方
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";//访问注册中心

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/dept/add")
	public boolean add(Dept dept)
	{
//		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
		return restTemplate.postForEntity(REST_URL_PREFIX + "/dept/add",dept,Boolean.class).getBody();
	}

	@RequestMapping(value = "/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id)
	{
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
		return restTemplate.getForEntity(REST_URL_PREFIX + "/dept/get/{1}",Dept.class,id ).getBody();
	}

	@RequestMapping(value = "/dept/list")
	public List<Dept> list()
	{
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}

	@RequestMapping(value = "/dept/discovery")
	public Object discovery()
	{
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery", Object.class);
	}


}
