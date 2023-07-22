package com.example.posts;

import com.example.posts.Controller.PostController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class PostsApplication {

	public static void main(String[] args) {

//		new File(PostController.uploadDirectory).mkdir();

		SpringApplication.run(PostsApplication.class, args);
	}

}
