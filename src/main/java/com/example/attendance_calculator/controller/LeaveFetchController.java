package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.service.HolidayFetchService;
import com.example.attendance_calculator.service.LeaveFetchService;
import com.example.attendance_calculator.service.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LeaveFetchController {

    private final LeaveFetchService leaveFetchService;

    public LeaveFetchController(LeaveFetchService leaveFetchService) {
        this.leaveFetchService = leaveFetchService;
    }

    @GetMapping("/fetch-leave")
    public String fetchUserData() {
        leaveFetchService.fetchAndStoreLeaveData();
        return "ESS holidays fetched and saved to table.";
    }
}

