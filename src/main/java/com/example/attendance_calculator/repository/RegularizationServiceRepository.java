package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Regularization;
import com.example.attendance_calculator.model.RegularizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularizationServiceRepository extends JpaRepository<Regularization, RegularizationId> {
}

