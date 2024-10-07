package eterea.core.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EtereaCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(EtereaCoreApplication.class, args);
	}

}
