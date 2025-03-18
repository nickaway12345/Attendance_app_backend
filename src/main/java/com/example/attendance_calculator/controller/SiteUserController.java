package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.SiteUsers;
import com.example.attendance_calculator.service.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/site-users")
public class SiteUserController {

    @Autowired
    private SiteUserService siteUserService;

    @GetMapping("/{empId}")
    public ResponseEntity<Map<String, String>> getUserSite(@PathVariable String empId) {
        System.out.println("Fetching site for username: " + empId);
        String user = siteUserService.getUserSite(empId);
        Map<String, String> response = new HashMap<>();
        response.put("site", user);
        if (user == null) {
            System.out.println("User not found in database.");
            throw new RuntimeException("User site not found");
        }
        System.out.println("User found: " + empId);
        return ResponseEntity.ok(response);
    }

}

