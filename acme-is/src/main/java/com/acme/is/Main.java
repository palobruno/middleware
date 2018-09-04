package com.acme.is;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/integration/activemq/amq-config.xml",
				"/META-INF/spring/integration/activemq/amq-operations.xml");

		System.out.println("Press Enter/Return to exit");
		System.in.read();
		context.close();
	}
}
