package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
    // Custom query if needed to find attendance records

    // Derived query to find attendance by empId
    List<Attendance> findByIdEmpId(String empId);

    Optional<Attendance> findById_EmpIdAndId_Date(String empId, String date);


}
