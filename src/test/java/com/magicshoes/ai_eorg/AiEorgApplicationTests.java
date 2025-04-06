package com.magicshoes.ai_eorg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class AiEorgApplicationTests {
	@MockitoBean
	private JwtDecoder jwtDecoder;

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	void contextLoads() {
	}

}
