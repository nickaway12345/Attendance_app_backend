package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.Regularize;
import com.example.attendance_calculator.service.AttendanceService;
import com.example.attendance_calculator.service.RegularizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/regularization")
public class RegularizationController {

    private static final Logger logger = LoggerFactory.getLogger(RegularizationController.class);

    @Autowired
    private RegularizeService regularizeService;

    @PostMapping("/sync")
    public ResponseEntity<String> syncregularize(@RequestBody Regularize regularizeRequest) {
        try {
            logger.info("Received Regularize Request: {}", regularizeRequest);
            // Call the service to sync attendance
            regularizeService.syncRegularize(regularizeRequest);

            String successMessage = "Regularize record synced successfully.";
            logger.info("Response: {}", successMessage);
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            String errorMessage = "Error syncing regularize record: " + e.getMessage();
            logger.error("Response: {}", errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
    }
}