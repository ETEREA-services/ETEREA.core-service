package eterea.core.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"app.enable-send-email=false"})
@EnableAutoConfiguration(exclude = {ConsulAutoServiceRegistrationAutoConfiguration.class})
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}

