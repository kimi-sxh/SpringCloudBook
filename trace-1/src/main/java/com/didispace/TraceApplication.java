package com.didispace;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class TraceApplication {

	private final Logger logger = Logger.getLogger(getClass());

//	@Bean
//	public AlwaysSampler defaultSampler() {
//		return new AlwaysSampler();
//	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/trace-1", method = RequestMethod.GET)
	public String trace() {
		logger.info("===<first call trace-2>===");
		String result = restTemplate().getForEntity("http://trace-2/trace-2", String.class).getBody();
		logger.info("===<first call trace-2 end>===");

		logger.info("===<second call trace-2>===");
		result = restTemplate().getForEntity("http://trace-2/trace-2", String.class).getBody();
		logger.info("===<second call trace-2 end>===");
		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(TraceApplication.class, args);
	}

}
