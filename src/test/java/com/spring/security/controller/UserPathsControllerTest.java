package com.spring.security.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig(TestConfig.class)  // integrates JUnit 5 with Spring TestContext
@WebAppConfiguration
public class UserPathsControllerTest {

	private MockMvc mockMvc;
	
	@BeforeEach
   public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders
        				.webAppContextSetup(wac)
        				.apply(SecurityMockMvcConfigurers.springSecurity())
        				.build();
    }
	 
  @Test
   public void testWelcomePage() throws Exception {
        mockMvc.perform(get("/welcome"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().string("{\"message\":\"Welcome to the Home Page. Please Choose to Login as a User or an Admin\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "USER" })
  public void testUserLogin() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isOk())
             .andExpect(content().contentType("application/json"))
             .andExpect(content().string("{\"message\":\"User Login Successful\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "ADMIN" })
  public void testUserLogin_Admin() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isOk())
             .andExpect(content().contentType("application/json"))
             .andExpect(content().string("{\"message\":\"User Login Successful\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "MANAGER" })
  public void testUserLogin_InvalidRole() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isForbidden());
  }
  
  @Test
  @WithMockUser(roles = { "USER" })
  public void testAdminLogin_InvalidRole() throws Exception {
      mockMvc.perform(get("/admin"))
             .andExpect(status().isForbidden());
  }
  
  @Test
  @WithMockUser(roles = { "ADMIN" })
  public void testAdminLogin() throws Exception {
      mockMvc.perform(get("/admin"))
	      .andExpect(status().isOk())
	      .andExpect(content().contentType("application/json"))
	      .andExpect(content().string("{\"message\":\"Admin Login Successful\"}"));
  }
  
  @Test
  public void testLogoutSuccessPage() throws Exception {
      mockMvc.perform(get("/logout-success"))
	      .andExpect(status().isOk())
	      .andExpect(content().contentType("text/html;charset=ISO-8859-1"));
  }

}
