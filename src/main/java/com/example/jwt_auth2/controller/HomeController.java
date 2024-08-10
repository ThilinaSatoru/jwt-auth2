package com.example.jwt_auth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello API __________ Login-(http://localhost:8083/auth/login), Register-(http://localhost:8083/auth/register)";
    }
}
