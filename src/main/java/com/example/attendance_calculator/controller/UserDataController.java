package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.service.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/fetch-userdata")
    public String fetchUserData() {
        userDataService.fetchAndStoreUserData();
        return "User data fetched and saved to JSON file.";
    }
}

