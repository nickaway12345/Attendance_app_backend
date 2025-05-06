package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.ShiftTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShiftTimingRepository extends JpaRepository<ShiftTiming, Long> {

    @Query("SELECT s FROM ShiftTiming s WHERE s.username = :username AND DATE(s.startTime) = :date")
    List<ShiftTiming> findShiftsByUsernameAndDate(@Param("username") String username, @Param("date") LocalDate date);

    List<ShiftTiming> findByUsername(String username);

    @Query("SELECT COUNT(s) > 0 FROM ShiftTiming s " +
            "WHERE s.username = :username AND s.site = :site " +
            "AND s.startTime = :startTime AND s.shiftNumber = :shiftNumber")
    boolean existsByUsernameAndSiteAndStartTimeAndShiftNumber(@Param("username") String username,
                                                              @Param("site") String site,
                                                              @Param("startTime") LocalDateTime startTime,
                                                              @Param("shiftNumber") int shiftNumber);

    @Modifying
    @Transactional
    @Query("UPDATE ShiftTiming s SET s.startTime = :startTime, s.endTime = :endTime " +
            "WHERE s.username = :username AND s.site = :site " +
            "AND s.startTime = :startTime AND s.shiftNumber = :shiftNumber")
    void updateShiftTiming(@Param("username") String username,
                           @Param("site") String site,
                           @Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime,
                           @Param("shiftNumber") int shiftNumber);



}
