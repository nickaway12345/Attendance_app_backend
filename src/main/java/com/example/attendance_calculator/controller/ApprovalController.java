package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.Regularize;
import com.example.attendance_calculator.model.RegularizeDTO;
import com.example.attendance_calculator.service.RegularizeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

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



    private static List<String> getReportees(String managerId) {
        List<String> reportees = new ArrayList<>();

        try {

            InputStream is = ApprovalController.class.getClassLoader().getResourceAsStream("assets/credentials.json");

            if (is == null) {
                throw new RuntimeException("File not found: assets/credentials.json");
            }


            String jsonString = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A").next();


            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray usersArray = jsonObject.getJSONArray("users");


            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);


                if (user.getString("manager").equals(managerId)) {
                    reportees.add(user.getString("username"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportees;
    }

}
