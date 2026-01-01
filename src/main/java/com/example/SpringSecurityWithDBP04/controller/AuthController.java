package com.example.SpringSecurityWithDBP04.controller;

import com.example.SpringSecurityWithDBP04.entity.UserEntity;
import com.example.SpringSecurityWithDBP04.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class AuthController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRole()==null || user.getRole().isBlank()){
            throw  new RuntimeException("user role required");
        }

        String role=user.getRole().toUpperCase();

        if(!role.equals("USER") && !role.equals("ADMIN")){
            throw new RuntimeException("Invalid Username: allowed role only: ADMIN, USER");
        }

        user.setRole("ROLE_"+role);

        userRepository.save(user);

        return "User registered successfully";
    }

}
