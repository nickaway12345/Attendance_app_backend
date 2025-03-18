package com.example.attendance_calculator.service;


import com.example.attendance_calculator.model.ShiftTiming;
import com.example.attendance_calculator.repository.ShiftTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftTimingRepository shiftTimingRepository;

    public List<ShiftTiming> getShiftsForUser(String username, LocalDate date) {
        return shiftTimingRepository.findShiftsByUsernameAndDate(username, date);
    }

    public List<ShiftTiming> getShiftTimingsByEmpId(String empId) {
        return shiftTimingRepository.findByUsername(empId);
    }



}

