// package com.crio.stayease.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.crio.stayease.dtos.LoginUserDto;
// import com.crio.stayease.dtos.RegisterUserDto;
// import com.crio.stayease.model.User;
// import com.crio.stayease.repository.UserRepository;

// @Service
// public class AuthenticationService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final AuthenticationManager authenticationManager;

//     @Autowired
//     public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.authenticationManager = authenticationManager;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public User signup(RegisterUserDto input) {
//         System.out.println("Authservice -> Signup called");
//         User user = new User();
//         user.setFirstName(input.getFirstName());
//         user.setLastName(input.getLastName());
//         user.setEmail(input.getEmail());
//         user.setPassword(passwordEncoder.encode(input.getPassword()));
//         return userRepository.save(user);
//     }

//     public User authenticate(LoginUserDto input) {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
//         return userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
//     }
// }
