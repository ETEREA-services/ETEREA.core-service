package eterea.core.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"app.enable-send-email=false"})
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}

