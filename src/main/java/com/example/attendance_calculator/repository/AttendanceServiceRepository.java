package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.AttendanceService;
import com.example.attendance_calculator.model.AttendanceServiceId;
import com.example.attendance_calculator.service.ServiceAttendanceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceServiceRepository extends JpaRepository<AttendanceService, AttendanceServiceId> {
    List<AttendanceService> findByIdEmpId(String empId);

    Optional<AttendanceService> findById(AttendanceServiceId id);
}
