package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.UserData;
import com.example.attendance_calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userdata/{empId}")
    public ResponseEntity<?> getUserData(@PathVariable String empId) {
        return userService.getUserDataByUsername(empId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}


