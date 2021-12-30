package com.gev.auth.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.gev.auth.token.JwtProperties;
import com.gev.auth.user.controller.UserController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootSecurityApplicationTests {

	@Autowired
	UserController controller;
	
	@Autowired
	MockMvc mock;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void testOKLogin() throws Exception {
		mock.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n"
				+ "    \"username\" : \"gev\",\n"
				+ "    \"password\" : \"1234\"\n"
				+ "}")).andDo(print()).andExpect(header().exists(JwtProperties.HEADER_STRING));
		 
	}
	
	@Test
	void testFaillureLogin() throws Exception {
		mock.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n"
				+ "    \"username\" : \"geva\",\n"
				+ "    \"password\" : \"123\"\n"
				+ "}")).andExpect(status().isUnauthorized());
	}
	
	@Test
	void testHello() throws Exception {
		MvcResult andReturn = mock.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n"
				+ "    \"username\" : \"gev\",\n"
				+ "    \"password\" : \"1234\"\n"
				+ "}")).andReturn();
		
		mock.perform(get("/hello")
				.header(JwtProperties.HEADER_STRING, andReturn.getResponse()
		.getHeader(JwtProperties.HEADER_STRING)));
	}
}
