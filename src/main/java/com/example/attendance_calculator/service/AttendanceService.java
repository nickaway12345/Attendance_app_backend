package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    public Optional<Attendance> getAttendanceByDate(String empId, String date) {
        return attendanceRepository.findById_EmpIdAndId_Date(empId, date);
    }

    public List<Attendance> getAttendanceByUserId(String empId) {
        return attendanceRepository.findByIdEmpId(empId);
    }

    public void syncAttendance(Attendance attendanceRequest) {
        // Log the received attendance payload
        logger.info("Received attendance payload: {}", attendanceRequest);

        // Log the latitude and longitude values for punch-in and punch-out
        logger.info("Punch-in Lat: {}, Punch-in Long: {}", attendanceRequest.getPunchInLat(), attendanceRequest.getPunchInLong());
        logger.info("Punch-out Lat: {}, Punch-out Long: {}", attendanceRequest.getPunchOutLat(), attendanceRequest.getPunchOutLong());



        // Save or update the attendance record
        attendanceRepository.save(attendanceRequest);
    }

}
