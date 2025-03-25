package com.bangjwo;

import com.bangjwo.common.crypto.RSAService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BangjwoApplicationTests {

	@MockBean
	private RSAService rsaService;

	@Test
	void contextLoads() {
	}

}
