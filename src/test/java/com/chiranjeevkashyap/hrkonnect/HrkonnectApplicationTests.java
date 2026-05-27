package com.chiranjeevkashyap.hrkonnect;

import com.chiranjeevkashyap.hrkonnect.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HrkonnectApplicationTests {
	@Autowired
	private UserService service;

	@Test
	void contextLoads() {
	}

}
