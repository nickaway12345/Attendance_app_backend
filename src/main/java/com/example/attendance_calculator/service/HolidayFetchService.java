package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.Holiday;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HolidayFetchService {

    @Value("${api.holiday.url}")
    private String API_URL;

    private final DataSource dataSource;

    public HolidayFetchService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    public void fetchAndStoreHolidayData() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Fetch holiday data from the API and map it to List<Holiday>
            ResponseEntity<List<Holiday>> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Holiday>>() {}
            );

            List<Holiday> holidays = response.getBody();

            if (holidays != null && !holidays.isEmpty()) {
                // Insert each holiday date into the database
                for (Holiday holiday : holidays) {
                    String formattedDate = formatDate(holiday.getDate()); // Transform date format
                    insertHolidayIntoDatabase(formattedDate);
                }
                System.out.println("Holiday data saved successfully in the database.");
            } else {
                System.err.println("No holiday data fetched from the API.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching or storing holiday data: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
        }
    }

    private String formatDate(String inputDate) throws ParseException {
        // Parse the input date (dd-MM-yyyy) and format it to yyyy-MM-dd
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = inputFormat.parse(inputDate);
        return outputFormat.format(date);
    }

    private void insertHolidayIntoDatabase(String date) {
        String sql = "INSERT INTO HOLIDAY (date) VALUES (?) ON CONFLICT (date) DO NOTHING";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error inserting holiday into database: " + e.getMessage());
        }
    }
}