package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Regularize;
import com.example.attendance_calculator.model.RegularizeDTO;
import com.example.attendance_calculator.service.RegularizeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

    @Value("${data.userDataFile.file}")
    private String FILE_PATH;

    @Autowired
    private RegularizeService regularizeService;

    // Get all pending entries for a manager or HR admin
    @GetMapping("/{managerId}/pending")
    public List<RegularizeDTO> getPendingEntries(@PathVariable String managerId) {

        List<String> reportees = getReportees(managerId); // Fetch reportees based on the managerId
        return regularizeService.getEntriesForApproval(reportees);
    }

    // Get all pending entries for a manager or HR admin
    @GetMapping("/{managerId}/rejected")
    public List<RegularizeDTO> getRejectedEntries(@PathVariable String managerId) {

        List<String> reportees = getReportees(managerId); // Fetch reportees based on the managerId
        return regularizeService.getRejectedEntries(reportees);
    }

    @GetMapping("/{managerId}/approved")
    public List<RegularizeDTO> getApprovedEntries(@PathVariable String managerId) {

        List<String> reportees = getReportees(managerId); // Fetch reportees based on the managerId
        return regularizeService.getApprovedEntries(reportees);
    }

    // Approve or reject an entry
    @PostMapping("/update")
    public Regularize updateApproval(@RequestParam String empId,
                                     @RequestParam String date,
                                     @RequestParam String approval,
                                     @RequestParam String approvedBy) {
        return regularizeService.updateApproval(empId, date, approval, approvedBy);
    }



    private List<String> getReportees(String managerId) {
        List<String> reportees = new ArrayList<>();

        try {
            // Read the file content
            String jsonString = Files.readString(Paths.get(FILE_PATH), StandardCharsets.UTF_8);

            // Parse the JSON array
            JSONArray usersArray = new JSONArray(jsonString);

            // Iterate through the array to find reportees
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);

                // Check if the "reporting_manager" field exists and is not null
                if (user.has("reporting_manager") && !user.isNull("reporting_manager")) {
                    String reportingManager = user.getString("reporting_manager");

                    // Compare with the provided managerId
                    if (reportingManager.equals(managerId)) {
                        reportees.add(user.getString("username"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading userdata.json", e);
        }

        return reportees;
    }

}
