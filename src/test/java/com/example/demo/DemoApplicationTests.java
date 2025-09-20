package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		// Test que le contexte Spring se charge correctement
	}

	@Test
	void testHomeEndpoint() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("221-java-projet")));
	}

	@Test
	void testStatusEndpoint() throws Exception {
		mockMvc.perform(get("/api/status"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("running")));
	}

	@Test
	void testHealthEndpoint() throws Exception {
		mockMvc.perform(get("/actuator/health"))
				.andExpect(status().isOk());
	}

}
