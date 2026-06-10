package com.scotiabank.corebanking.controller;

import com.scotiabank.corebanking.security.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        if ("password123".equals(password)){
            System.out.println("Login successful for " + username);
            return jwtService.generateToken(username);
        } else {
            throw new RuntimeException("Incorrect Password");
        }
    }
}
