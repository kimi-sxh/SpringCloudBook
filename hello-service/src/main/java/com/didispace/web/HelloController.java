package com.didispace.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class HelloController {

	private static Map<Long,User> dbMap = new ConcurrentHashMap<>();

	static {
		String uuid = UUID.randomUUID().toString();
		dbMap.put(1L,new User(1L,uuid,"kimi",30));
		uuid = UUID.randomUUID().toString();
		dbMap.put(2L,new User(2L,uuid,"tracy",29));
		uuid = UUID.randomUUID().toString();
		dbMap.put(3L,new User(3L,uuid,"nancy",28));
	}
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws Exception {
		ServiceInstance instance = client.getLocalServiceInstance();

		// 测试超时触发断路器
		int sleepTime = new Random().nextInt(3000);
		logger.info("sleepTime:" + sleepTime);
		Thread.sleep(sleepTime);

		logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello World";
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam String name) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello1, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello2, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return new User(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody User user) {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello3, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello "+ user.getName() + ", " + user.getAge();
	}

	@RequestMapping(value = "/hello4", method = RequestMethod.GET)
	public User hello4(@RequestParam(name = "id") Long id) {
		User user = dbMap.get(id);
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info(user + "/hello4, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return user;
	}

	@RequestMapping(value = "/hello5", method = RequestMethod.POST)
	public String hello5(@RequestBody User user) {
		User dbUser = dbMap.get(user.getId());
		dbUser = user;
		return "success";
	}

}