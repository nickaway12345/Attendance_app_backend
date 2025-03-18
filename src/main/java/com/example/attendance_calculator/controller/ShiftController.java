package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.ShiftTiming;
import com.example.attendance_calculator.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @GetMapping("/{username}")
    public List<ShiftTiming> getShifts(@PathVariable String username, @RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return shiftService.getShiftsForUser(username, parsedDate);
    }

    @GetMapping("/shift_timings")
    public List<ShiftTiming> getShiftTimings(@RequestParam String empId) {
        return shiftService.getShiftTimingsByEmpId(empId);
    }


}

