package com.p4u.core.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.p4u.core.config.JerseyConfig;

@SpringBootApplication
@ComponentScan(basePackageClasses=JerseyConfig.class)
public class P4uApplication {
	public static void main(String[] args) {
		SpringApplication.run(P4uApplication.class, args);
	}
}
