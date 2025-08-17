package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPathsController {

    @GetMapping(value = "/Welcome", produces = "application/json")
    public String welcome() {
        return "Welcome";
    }
}
