package com.beat.oven.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.beat.oven.producer.IProducerable;
import com.beat.oven.producer.ProduceMidi;

@SpringBootApplication
@ComponentScan(basePackages = {"com.beat.oven.controller"})
public class OvenApplication {
	
	@Bean
	IProducerable produceMidi() {
		return new ProduceMidi();
	}

	public static void main(String[] args) {
		SpringApplication.run(OvenApplication.class, args);
	}

}
