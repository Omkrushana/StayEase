package com.crio.stayease.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.stayease.dtos.RegisterUserDto;
import com.crio.stayease.model.Role;
import com.crio.stayease.model.User;
import com.crio.stayease.repository.UserRepository;
import com.crio.stayease.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CustomUserDetailsService userdetails;

    @PostMapping("/signup")
     public ResponseEntity<?> register(@RequestBody User user) 
     {
        user.setRole(Role.CUSTOMER);
        ResponseEntity<?>  registeredUser = userdetails.registerUser(user);
        log.info("Received registration request for email: {}");
        return registeredUser;
    }

    @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody User user) {
        user.setRole(Role.MANAGER);
        ResponseEntity<?>  authenticated = userdetails.authenticateUser(user);
        log.info("Received authentication request for email: {}");
        return authenticated;
    }

    @PostMapping("/signup/manager")
    public ResponseEntity<?> registerManager(@RequestBody User user) {
        user.setRole(Role.MANAGER);
        ResponseEntity<?> registeredUser = userdetails.registerManager(user);
        log.info("Received manager registration request for email: {}", user.getEmail());
        return registeredUser;
    }

    @PostMapping("/login/manager")
    public ResponseEntity<?> loginManager(@RequestBody User user) {
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has a MANAGER role
        if (!foundUser.getRole().equals(Role.MANAGER)) {
            throw new BadCredentialsException("User is not a manager");
        }

        ResponseEntity<?> authenticated = userdetails.authenticateUser(user);
        log.info("Received manager authentication request for email: {}", user.getEmail());
        return authenticated;
    }

    // @PostMapping("/login")
    // public UsernamePasswordAuthenticationToken loginUser(@RequestBody User loginUser) {
    //     System.out.println("Login endpoint hit");
    //     User user = userRepository.findByEmail(loginUser.getEmail())
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    //     System.out.println(user.toString());

    //     // Compare the provided password with the encoded password in the database
    //     String rawPassword = loginUser.getPassword();
    //     String encodedPasswordFromDB = user.getPassword();

    //     System.out.println("Raw password from payload: " + rawPassword);
    //     System.out.println("Encoded password from DB: " + encodedPasswordFromDB);

    //     boolean matches = passwordEncoder.matches(rawPassword, encodedPasswordFromDB);

    //     System.out.println("Passwords match: " + matches);

    //     if (matches) {
    //         return new UsernamePasswordAuthenticationToken(
    //                 user.getEmail(),
    //                 user.getPassword(),
    //                 new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
    //                         new ArrayList<>()).getAuthorities());
    //         // return "Login successful";
    //     } else {
    //         throw new BadCredentialsException("Incorrect user credentials !!");
    //         // return "Login failed. Invalid credentials";
    //     }
    // }

}
