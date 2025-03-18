package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.AttendanceServiceId;
import com.example.attendance_calculator.repository.AttendanceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.attendance_calculator.model.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAttendanceService {
    @Autowired
    private AttendanceServiceRepository attendanceServiceRepository;

    public void saveAttendance(AttendanceService attendance) {
        attendanceServiceRepository.save(attendance);
    }

    public List<AttendanceService> getAttendanceByEmpId(String empId) {
        return attendanceServiceRepository.findByIdEmpId(empId);
    }

    public Optional<AttendanceService> getAttendanceData(String empId, String date, int shiftNumber) {

        LocalDate Ldate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        AttendanceServiceId attendanceId = new AttendanceServiceId(empId, Ldate, shiftNumber);
        return attendanceServiceRepository.findById(attendanceId);

    }
}

