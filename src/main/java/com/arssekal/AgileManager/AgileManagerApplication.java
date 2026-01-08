package com.arssekal.AgileManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
@SpringBootApplication
public class AgileManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgileManagerApplication.class, args);
		System.out.println("running...");
	}
}