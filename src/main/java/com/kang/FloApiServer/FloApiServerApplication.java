package com.kang.FloApiServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling 
@SpringBootApplication
public class FloApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloApiServerApplication.class, args);
	}
}
