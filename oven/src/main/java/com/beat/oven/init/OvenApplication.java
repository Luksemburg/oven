package com.beat.oven.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.beat.oven.controller"})
public class OvenApplication {

	public static void main(String[] args) {
		SpringApplication.run(OvenApplication.class, args);
	}

}
