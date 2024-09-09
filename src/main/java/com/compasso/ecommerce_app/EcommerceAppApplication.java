package com.compasso.ecommerce_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EcommerceAppApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.datasource.url", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		System.setProperty("spring.mail.username", dotenv.get("SPRING_MAIL_USERNAME"));
		System.setProperty("spring.mail.password", dotenv.get("SPRING_MAIL_PASSWORD"));
		System.setProperty("spring.mail.email.remetente", dotenv.get("SPRING_MAIL_EMAIL_REMETENTE"));
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

}
