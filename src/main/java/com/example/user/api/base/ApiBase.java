package com.example.user.api.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiBase {
	protected ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
}
