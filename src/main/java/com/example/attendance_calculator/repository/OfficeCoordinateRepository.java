package com.example.attendance_calculator.repository;


import com.example.attendance_calculator.model.OfficeCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeCoordinateRepository extends JpaRepository<OfficeCoordinate, Long> {
}

