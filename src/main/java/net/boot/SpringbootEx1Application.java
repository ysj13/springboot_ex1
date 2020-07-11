package net.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootEx1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEx1Application.class, args);
	}

}
