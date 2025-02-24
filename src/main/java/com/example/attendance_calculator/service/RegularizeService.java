package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.*;
import com.example.attendance_calculator.repository.AttendanceRepository;
import com.example.attendance_calculator.repository.RegularizeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegularizeService {

    @Autowired
    private RegularizeRepository regularizeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(RegularizeService.class);

    public void syncRegularize(Regularize regularizeRequest) {
        // Log the received attendance payload
        logger.info("Received regularize payload: {}", regularizeRequest);

        // Log the latitude and longitude values for punch-in and punch-out
        logger.info("Punch-in Lat: {}, Punch-in Long: {}", regularizeRequest.getPunchInLat(), regularizeRequest.getPunchInLong());
        logger.info("Punch-out Lat: {}, Punch-out Long: {}", regularizeRequest.getPunchOutLat(), regularizeRequest.getPunchOutLong());



        // Save or update the attendance record
        regularizeRepository.save(regularizeRequest);
    }

    public List<RegularizeDTO> getEntriesForApproval(List<String> reportees) {
        List<RegularizeDTO> pendingEntries = new ArrayList<>();

        // Iterate through each reportee (empId)
        for (String empId : reportees) {
            // Fetch all Regularize entries for the given empId where approval is "Pending"
            List<Regularize> regularizeEntries = regularizeRepository.findByEmpIdAndApproval(empId, "Pending");


            // Iterate through the fetched entries and map to DTOs
            for (Regularize reg : regularizeEntries) {
                RegularizeDTO dto = new RegularizeDTO();
                dto.setEmpId(reg.getId().getEmpId());
                dto.setDate(reg.getId().getDate());
                dto.setNewInTime(reg.getInTime());
                dto.setNewOutTime(reg.getOutTime());
                dto.setLocationIn(reg.getLocationIn());
                dto.setLocationOut(reg.getLocationOut());
                dto.setTotalHours(reg.getTotalHours());
                dto.setApprovalStatus(reg.getApproval());

                // Add the DTO to the result list
                pendingEntries.add(dto);
            }
        }

        return pendingEntries; // Return all pending entries for reportees
    }

    public List<RegularizeDTO> getRejectedEntries(List<String> reportees) {
        List<RegularizeDTO> pendingEntries = new ArrayList<>();

        // Iterate through each reportee (empId)
        for (String empId : reportees) {
            // Fetch all Regularize entries for the given empId where approval is "Pending"
            List<Regularize> regularizeEntries = regularizeRepository.findByEmpIdAndApproval(empId, "Rejected");


            // Iterate through the fetched entries and map to DTOs
            for (Regularize reg : regularizeEntries) {
                RegularizeDTO dto = new RegularizeDTO();
                dto.setEmpId(reg.getId().getEmpId());
                dto.setDate(reg.getId().getDate());
                dto.setNewInTime(reg.getInTime());
                dto.setNewOutTime(reg.getOutTime());
                dto.setLocationIn(reg.getLocationIn());
                dto.setLocationOut(reg.getLocationOut());
                dto.setTotalHours(reg.getTotalHours());
                dto.setApprovalStatus(reg.getApproval());

                // Add the DTO to the result list
                pendingEntries.add(dto);
            }
        }

        return pendingEntries; // Return all pending entries for reportees
    }

    public List<RegularizeDTO> getApprovedEntries(List<String> reportees) {
        List<RegularizeDTO> pendingEntries = new ArrayList<>();

        // Iterate through each reportee (empId)
        for (String empId : reportees) {
            // Fetch all Regularize entries for the given empId where approval is "Pending"
            List<Regularize> regularizeEntries = regularizeRepository.findByEmpIdAndApproval(empId, "Approved");


            // Iterate through the fetched entries and map to DTOs
            for (Regularize reg : regularizeEntries) {
                RegularizeDTO dto = new RegularizeDTO();
                dto.setEmpId(reg.getId().getEmpId());
                dto.setDate(reg.getId().getDate());
                dto.setNewInTime(reg.getInTime());
                dto.setNewOutTime(reg.getOutTime());
                dto.setLocationIn(reg.getLocationIn());
                dto.setLocationOut(reg.getLocationOut());
                dto.setTotalHours(reg.getTotalHours());
                dto.setApprovalStatus(reg.getApproval());

                // Add the DTO to the result list
                pendingEntries.add(dto);
            }
        }

        return pendingEntries; // Return all pending entries for reportees
    }


    public Regularize updateApproval(String empId, String date, String approval, String approvedBy) {
        RegularizeId id = new RegularizeId(empId, date);
        Optional<Regularize> regularize = regularizeRepository.findById(id);

        if (regularize.isPresent()) {
            Regularize reg = regularize.get();
            reg.setApproval(approval);
            reg.setApproved_by(approvedBy);
            reg.setApproved_at(LocalDateTime.now());
            Regularize updatedRegularize = regularizeRepository.save(reg);

            // Now update the attendance table
            AttendanceId attendanceId = new AttendanceId(empId, date); // Assuming similar composite key
            Optional<Attendance> attendance = attendanceRepository.findById(attendanceId);

            String inTime = updatedRegularize.getInTime();
            String outTime = updatedRegularize.getOutTime();
            String locationIn = updatedRegularize.getLocationIn();
            String locationOut = updatedRegularize.getLocationOut();
            Double totalHours = updatedRegularize.getTotalHours();
            String day = totalHours >= 9 ? "F" : "H";

            // If locationIn or locationOut is null or empty, set them to "Outside Office"
            if (locationIn == null || locationIn.isEmpty()) {
                locationIn = "Outside Office";
            }
            if (locationOut == null || locationOut.isEmpty()) {
                locationOut = "Outside Office";
            }

            if (attendance.isPresent()) {
                // Update the existing entry in the attendance table
                Attendance att = attendance.get();
                att.setInTime(inTime);
                att.setOutTime(outTime);
                att.setLocationIn(locationIn);
                att.setLocationOut(locationOut);
                att.setTotalHours(totalHours);
                att.setDay(day);
                logger.info("Updating Attendance: " + att.getInTime() + " " + att.getOutTime() + " " + att.getLocationIn()
                        + " " + att.getLocationOut() + " " + att.getTotalHours() + " " + att.getDay());
                attendanceRepository.save(att);

            } else {
                // Create a new entry in the attendance table
                Attendance newAttendance = new Attendance();
                AttendanceId newAttendanceId = new AttendanceId(empId, date);
                newAttendance.setId(newAttendanceId); // Set the composite key
                newAttendance.setInTime(inTime);
                newAttendance.setOutTime(outTime);
                newAttendance.setLocationIn(locationIn);
                newAttendance.setLocationOut(locationOut);
                newAttendance.setTotalHours(totalHours);
                newAttendance.setDay(day);
                logger.info("Creating New Attendance: " + newAttendance.getInTime() + " " + newAttendance.getOutTime()
                        + " " + newAttendance.getLocationIn() + " " + newAttendance.getLocationOut() + " " + newAttendance.getTotalHours()
                        + " " + newAttendance.getDay());
                attendanceRepository.save(newAttendance);
            }

            return updatedRegularize;
        }

        throw new RuntimeException("Entry not found for empId: " + empId + " and date: " + date);
    }


}
