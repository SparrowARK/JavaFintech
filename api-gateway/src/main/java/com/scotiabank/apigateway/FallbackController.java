package com.scotiabank.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/bank")
    public String bankFallback() {
        return "⚠️ The Core Banking Service is currently down for emergency maintenance. Your funds are safe. Please try again in a few minutes.";
    }
}