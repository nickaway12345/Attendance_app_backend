package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.dto.LoginRequest;
import com.example.attendance_calculator.model.User;
import com.example.attendance_calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.validateCredentials(loginRequest);
        if (user != null) {
            return ResponseEntity.ok(user); // Return user details if validation succeeds
        } else {
            return ResponseEntity.status(401).body("Invalid credentials"); // Return error if validation fails
        }
    }
}

