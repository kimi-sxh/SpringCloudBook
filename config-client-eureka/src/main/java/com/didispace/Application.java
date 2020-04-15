package com.didispace;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@EnableDiscoveryClient//用来发现config-server服务
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}


//	@Bean
//	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//
//		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//
//		c.setIgnoreUnresolvablePlaceholders(true);
//
//		return c;
//
//	}

}
