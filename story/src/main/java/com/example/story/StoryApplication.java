package com.example.story;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoryApplication {

	public static void main(String[] args) {
		System.out.println("Starting..");
		SpringApplication.run(StoryApplication.class, args);
		System.out.println("Finishing..");
	}

}
