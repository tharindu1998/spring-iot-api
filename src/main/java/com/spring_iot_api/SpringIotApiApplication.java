package com.spring_iot_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringIotApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIotApiApplication.class, args);
	}


}
