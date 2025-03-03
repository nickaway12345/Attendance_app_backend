package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.Leave;
import com.example.attendance_calculator.model.LeaveId;
import com.example.attendance_calculator.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    public List<LeaveId> getLeaveByUserId(String empId) {
        return leaveRepository.findByUsername(empId);
    }
}
