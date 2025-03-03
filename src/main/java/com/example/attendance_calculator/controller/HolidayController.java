package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Holiday;
import com.example.attendance_calculator.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays")
    public List<Holiday> getHolidays() {
        return holidayService.getAllHolidays();
    }
}