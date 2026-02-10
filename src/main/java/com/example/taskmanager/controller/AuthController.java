package com.example.taskmanager.controller;

import com.example.taskmanager.config.JwtUtil;
import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.LoginResponse;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // For stateless JWT, logout is done on client by deleting token
        return ResponseEntity.ok("Logged out successfully");
    }
}
