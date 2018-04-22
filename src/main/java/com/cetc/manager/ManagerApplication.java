package com.cetc.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// jar
//@SpringBootApplication
//public class ManagerApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ManagerApplication.class, args);
//	}
//}

// war
@SpringBootApplication
public class ManagerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ManagerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}
