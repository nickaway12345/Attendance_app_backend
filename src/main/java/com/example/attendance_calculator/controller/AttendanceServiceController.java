package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.model.AttendanceService;
import com.example.attendance_calculator.service.ServiceAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance/service")
@RequiredArgsConstructor
public class AttendanceServiceController {

    @Autowired
    private ServiceAttendanceService attendanceService;

    @PostMapping("/sync")
    public ResponseEntity<String> syncAttendance(@RequestBody AttendanceService attendance) {
        attendanceService.saveAttendance(attendance);
        return ResponseEntity.ok("Attendance synced successfully.");
    }

    @GetMapping("/attendancedata")
    public List<AttendanceService> getAttendance(@RequestParam String empId) {
        return attendanceService.getAttendanceByEmpId(empId);
    }

    @GetMapping("/attendancedata_date")
    public Optional<AttendanceService> getAttendanceData(
            @RequestParam String empId, @RequestParam String date, @RequestParam int shiftNumber) {
        return attendanceService.getAttendanceData(empId, date, shiftNumber);
    }
}

