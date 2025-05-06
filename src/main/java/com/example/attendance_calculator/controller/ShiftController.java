package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.ShiftTiming;
import com.example.attendance_calculator.service.ShiftService;
import com.example.attendance_calculator.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Value("${api.roster.url}")
    private String apiUrl;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ShiftUserService shiftUserService;

    @GetMapping("/{username}")
    public List<ShiftTiming> getShifts(@PathVariable String username, @RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return shiftService.getShiftsForUser(username, parsedDate);
    }

    @GetMapping("/shift_timings")
    public List<ShiftTiming> getShiftTimings(@RequestParam String empId) {
        return shiftService.getShiftTimingsByEmpId(empId);
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateShifts() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(apiUrl, List.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            shiftUserService.processShiftData(response.getBody());
            return ResponseEntity.ok("Shift timings updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch shift data");
        }
    }


}

