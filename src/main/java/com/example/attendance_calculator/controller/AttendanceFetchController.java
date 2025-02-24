package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://192.168.10.17:8000")
public class AttendanceFetchController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/attendance")
    public List<Attendance> getAttendance(@RequestParam String empId) {
        return attendanceService.getAttendanceByUserId(empId);
    }

    @GetMapping("/attendanceByDate")
    public ResponseEntity<Attendance> getAttendanceByDate(
            @RequestParam String empId,
            @RequestParam String date) {

        Optional<Attendance> attendance = attendanceService.getAttendanceByDate(empId, date);

        return attendance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}