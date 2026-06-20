package com.chiranjeevkashyap.hrkonnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class HrkonnectApplicationTests {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void encodePassword() {
		System.out.println(passwordEncoder.encode("Priya@123"));
	}

}
