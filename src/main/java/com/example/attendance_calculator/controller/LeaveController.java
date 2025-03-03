package com.example.attendance_calculator.controller;
import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.Holiday;
import com.example.attendance_calculator.model.LeaveId;
import com.example.attendance_calculator.service.HolidayService;
import com.example.attendance_calculator.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/leaves")
    public List<LeaveId> getAttendance(@RequestParam String empId) {
        return leaveService.getLeaveByUserId(empId);
    }
}