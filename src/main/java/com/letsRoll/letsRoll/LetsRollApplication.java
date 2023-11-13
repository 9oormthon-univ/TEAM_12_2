package com.letsRoll.letsRoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LetsRollApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsRollApplication.class, args);
	}

}
