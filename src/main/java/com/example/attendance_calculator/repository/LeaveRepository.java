package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Holiday;
import com.example.attendance_calculator.model.Leave;
import com.example.attendance_calculator.model.LeaveId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveId, Long> {
    List<LeaveId> findByUsername(String username);
}