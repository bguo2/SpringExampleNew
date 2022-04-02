package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthCheck {

    private String activeProfile;

    @Autowired
    public HealthCheck(@Value("${spring.profiles.active}") String activeProfile) {
        this.activeProfile = activeProfile;
    }

    @GetMapping("/ping")
    public String ping() {
        return "activate profile: " + activeProfile;
    }
}
