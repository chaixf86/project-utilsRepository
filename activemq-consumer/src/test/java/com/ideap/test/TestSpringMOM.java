package com.ideap.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMOM {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent-email.xml");
		System.out.println("ÈÝÆ÷ÒÑÆô¶¯¡­¡­");
	}

}
