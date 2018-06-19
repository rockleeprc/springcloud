package com.example;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootAppApplication.class, args);
		SpringApplication app = new SpringApplication(SpringBootApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
