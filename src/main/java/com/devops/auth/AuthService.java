package com.devops.auth.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devops.auth.entity.User;
import com.devops.auth.repository.UserRepository;
import com.devops.auth.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    private final JwtService jwtService;


    public void register(User user){

        user.setPassword(
                encoder.encode(
                        user.getPassword()));

        user.setRole("USER");

        repo.save(user);
    }


    public String login(User user){

        User dbUser =
                repo.findByUsername(
                        user.getUsername())
                        .orElseThrow();

        if(encoder.matches(
                user.getPassword(),
                dbUser.getPassword())){

            return jwtService.generateToken(
                    dbUser.getUsername());
        }

        throw new RuntimeException();
    }
}
