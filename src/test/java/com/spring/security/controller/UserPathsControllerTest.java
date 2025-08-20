package com.spring.security.controller;

import org.junit.jupiter.api.BeforeAll;
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

	private static MockMvc mockMvc;
	
	@BeforeAll
   public static void setup(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders
        				.webAppContextSetup(wac)
        				.apply(SecurityMockMvcConfigurers.springSecurity()) // ensures TestSecurityContextHolder is used
        				.build();
    }
	 
  @Test
  @WithMockUser(roles = { "ADMIN" })
   public void testWelcomePage_AdminRole() throws Exception {
        mockMvc.perform(get("/welcome"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().string("{\"message\":\"Welcome to the Home Page!\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "USER" })
   public void testWelcomePage_UserRole() throws Exception {
        mockMvc.perform(get("/welcome"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().string("{\"message\":\"Welcome to the Home Page!\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "TEST" })
   public void testWelcomePage_TestRole() throws Exception {
        mockMvc.perform(get("/welcome"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().string("{\"message\":\"Welcome to the Home Page!\"}"));
  }
  
  @Test
   public void testWelcomePage_Unauthenticated() throws Exception {
        mockMvc.perform(get("/welcome"))
        	   .andExpect(status().isUnauthorized());
  }
  
  @Test
  @WithMockUser(roles = { "USER" })
  public void testGetUserData() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isOk())
             .andExpect(content().contentType("application/json"))
             .andExpect(content().string("{\"message\":\"User Login Successful\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "ADMIN" })
  public void testGetUserData_Admin() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isOk())
             .andExpect(content().contentType("application/json"))
             .andExpect(content().string("{\"message\":\"User Login Successful\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "TEST" })
  public void testGetUserData_InvalidRole() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isForbidden());
  }
  
  @Test
  public void testGetUserData_Unauthenticated() throws Exception {
      mockMvc.perform(get("/user"))
             .andExpect(status().isUnauthorized());
  }
  
  @Test
  @WithMockUser(roles = { "ADMIN" })
  public void testGetAdminData() throws Exception {
      mockMvc.perform(get("/admin"))
	      .andExpect(status().isOk())
	      .andExpect(content().contentType("application/json"))
	      .andExpect(content().string("{\"message\":\"Admin Login Successful\"}"));
  }
  
  @Test
  @WithMockUser(roles = { "USER" })
  public void testGetAdminData_InvalidRole() throws Exception {
      mockMvc.perform(get("/admin"))
             .andExpect(status().isForbidden());
  }
  
  @Test
  public void testGetAdminData_Unauthenticated() throws Exception {
      mockMvc.perform(get("/admin"))
             .andExpect(status().isUnauthorized());
  }
}
