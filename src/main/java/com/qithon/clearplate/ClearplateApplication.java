package com.qithon.clearplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClearplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearplateApplication.class, args);
	}

}
