package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}