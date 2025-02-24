package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://192.168.10.17:8000")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/sync")
    public ResponseEntity<String> syncAttendance(@RequestBody Attendance attendanceRequest) {
        try {
            // Call the service to sync attendance
            attendanceService.syncAttendance(attendanceRequest);

            return ResponseEntity.ok("Attendance record synced successfully.");
        } catch (Exception e) {
            // Handle any exceptions that may occur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error syncing attendance record: " + e.getMessage());
        }
    }
}
