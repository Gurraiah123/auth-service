package com.devops.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.devops.auth.entity.User;
import com.devops.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        service.register(user);

        return "registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return service.login(user);
    }
}
