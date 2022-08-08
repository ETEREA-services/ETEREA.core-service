package eterea.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EtereaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtereaApplication.class, args);
	}

}
