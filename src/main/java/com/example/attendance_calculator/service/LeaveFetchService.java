package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.Leave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveFetchService {

    @Value("${api.leave.url}")
    private String API_URL;

    private final DataSource dataSource;

    public LeaveFetchService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    public void fetchAndStoreLeaveData() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Fetch leave data from the API and map it to List<Leave>
            ResponseEntity<List<Leave>> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Leave>>() {}
            );

            List<Leave> leaves = response.getBody();

            if (leaves != null && !leaves.isEmpty()) {
                // Process each leave entry
                for (Leave leave : leaves) {
                    List<String> leaveDates = generateDateRange(leave.getStartDate(), leave.getEndDate());
                    for (String date : leaveDates) {
                        insertLeaveIntoDatabase(date, leave.getStatus(), leave.getLeaveType(), leave.getUsername());
                    }
                }
                System.out.println("Leave data saved successfully in the database.");
            } else {
                System.err.println("No leave data fetched from the API.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching or storing leave data: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
        }
    }

    private List<String> generateDateRange(long startDateEpoch, long endDateEpoch) {
        List<String> dateRange = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long currentDateEpoch = startDateEpoch;
        while (currentDateEpoch <= endDateEpoch) {
            Date currentDate = new Date(currentDateEpoch);
            dateRange.add(dateFormat.format(currentDate));
            currentDateEpoch += 86400000; // Add 1 day in milliseconds
        }

        return dateRange;
    }

    private void insertLeaveIntoDatabase(String date, String status, String leaveType, String username) {
        String sql = "INSERT INTO LEAVE (date, status, leave_type, username) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            statement.setString(2, status);
            statement.setString(3, leaveType);
            statement.setString(4, username);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error inserting leave into database: " + e.getMessage());
        }
    }
}