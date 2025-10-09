package com.example.InventarioPlus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InventarioPlusApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InventarioPlusApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(InventarioPlusApplication.class, args);
	}

}
