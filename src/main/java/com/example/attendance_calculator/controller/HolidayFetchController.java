package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.service.HolidayFetchService;
import com.example.attendance_calculator.service.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolidayFetchController {

    private final HolidayFetchService holidayFetchService;

    public HolidayFetchController(HolidayFetchService holidayFetchService) {
        this.holidayFetchService = holidayFetchService;
    }

    @GetMapping("/fetch-holidays")
    public String fetchUserData() {
        holidayFetchService.fetchAndStoreHolidayData();
        return "ESS holidays fetched and saved to table.";
    }
}

