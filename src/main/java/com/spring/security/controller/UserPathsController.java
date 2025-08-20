package com.spring.security.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserPathsController {

	
	// Home Page
	@ResponseBody
    @GetMapping(value = "/welcome", produces = "application/json")
    public Map<String, String> welcomePage() {
        return Map.of("message", "Welcome to the Home Page!");
    }
    
	@ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/user", produces = "application/json")
    public Map<String, String> getUserData() {
        return Map.of("message", "User Login Successful");
    } 
    
	@ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin", produces = "application/json")
    public Map<String, String> getAdminData() {
        return Map.of("message", "Admin Login Successful");
    }
    
}
