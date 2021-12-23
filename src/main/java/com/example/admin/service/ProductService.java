package com.example.admin.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductService  {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
}