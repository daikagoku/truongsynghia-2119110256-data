package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.user.template.ProductTemplate;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("Start Main");
		SpringApplication.run(Application.class, args);	
	}

}
