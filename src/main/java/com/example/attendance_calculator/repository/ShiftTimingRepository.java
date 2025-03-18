package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.ShiftTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShiftTimingRepository extends JpaRepository<ShiftTiming, Long> {

    @Query("SELECT s FROM ShiftTiming s WHERE s.username = :username AND DATE(s.startTime) = :date")
    List<ShiftTiming> findShiftsByUsernameAndDate(@Param("username") String username, @Param("date") LocalDate date);

    List<ShiftTiming> findByUsername(String username);






}
