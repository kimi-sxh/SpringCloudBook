package com.didispace;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication {

	/**
	 * <b>概要：</b>:
	 * 		定义全局日志级别
	 * <b>作者：</b>SUXH</br>
	 * <b>日期：</b>2020/4/3 10:22 </br>
	 * @param:
	 * @return:
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@PreDestroy
	public void preDestroy() throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("Application is destroyed");
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
