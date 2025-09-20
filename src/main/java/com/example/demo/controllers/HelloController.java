package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Bienvenue sur 221-java-projet! 🚀";
    }


    @GetMapping("/api/status")
    public String status() {
        return "Application is running successfully!";
    }
}
