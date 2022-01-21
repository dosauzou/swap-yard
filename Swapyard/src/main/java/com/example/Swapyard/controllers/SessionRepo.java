package com.example.Swapyard.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RequestMapping("/api/v1/")
public class SessionRepo {

    @RequestMapping("token")
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }
    @Bean
    HeaderHttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
}
