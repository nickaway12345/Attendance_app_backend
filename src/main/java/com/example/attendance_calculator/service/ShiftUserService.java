package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.ShiftTiming;
import com.example.attendance_calculator.repository.ShiftTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiftUserService {

    @Value("${api.roster.url}")
    private String API_URL;

    @Autowired
    private ShiftTimingRepository shiftTimingRepository;

    private static final Map<String, List<LocalTime>> SHIFT_TIMINGS = new HashMap<>();


    static {
        SHIFT_TIMINGS.put("NTT Chandivali", Arrays.asList(
                LocalTime.of(8, 0), LocalTime.of(15, 0),
                LocalTime.of(15, 0), LocalTime.of(22, 0),
                LocalTime.of(22, 0), LocalTime.of(8, 0),
                LocalTime.of(9, 30), LocalTime.of(17, 30)
        ));

        SHIFT_TIMINGS.put("NTT Banglore", Arrays.asList(
                LocalTime.of(7, 0), LocalTime.of(15, 0),
                LocalTime.of(14, 0), LocalTime.of(22, 0),
                LocalTime.of(22, 0), LocalTime.of(7, 0),
                LocalTime.of(10, 0), LocalTime.of(18, 0)
        ));


        SHIFT_TIMINGS.put("Sify", Arrays.asList(
                LocalTime.of(7, 0), LocalTime.of(15, 0),
                LocalTime.of(14, 0), LocalTime.of(22, 0),
                LocalTime.of(22, 0), LocalTime.of(7, 0),
                LocalTime.of(10, 0), LocalTime.of(18, 0)
        ));
    }


    @Scheduled(cron = "0 0 0 * * ?") // Runs every night at 12 AM
    public void updateShiftTimingsAutomatically() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(API_URL, List.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            processShiftData(response.getBody());
            System.out.println("Shift timings updated successfully at midnight");
        } else {
            System.err.println("Failed to fetch shift data at midnight");
        }
    }

    @Transactional
    public void processShiftData(List<Map<String, Object>> apiData) {
        for (Map<String, Object> record : apiData) {
            String site = (String) record.get("site");
            LocalDate date = Instant.ofEpochMilli((Long) record.get("date"))
                    .atZone(ZoneId.of("Asia/Kolkata"))
                    .toLocalDate();

            Map<String, Integer> shiftMapping = Map.of(
                    "a_shift", 1,
                    "b_shift", 2,
                    "c_shift", 3,
                    "g_shift", 4
            );

            for (String shiftKey : shiftMapping.keySet()) {
                String username = (String) record.get(shiftKey);
                if (username != null && !"NA".equals(username)) {
                    int shiftNumber = shiftMapping.get(shiftKey);
                    List<LocalTime> shiftTimes = SHIFT_TIMINGS.get(site);

                    if (shiftTimes != null) {
                        LocalDateTime startTime = LocalDateTime.of(date, shiftTimes.get((shiftNumber - 1) * 2));
                        LocalDateTime endTime = LocalDateTime.of(date, shiftTimes.get((shiftNumber - 1) * 2 + 1));

                        // Check if the record exists
                        boolean exists = shiftTimingRepository.existsByUsernameAndSiteAndStartTimeAndShiftNumber(username, site, startTime, shiftNumber);
                        if (exists) {
                            // Update existing record
                            shiftTimingRepository.updateShiftTiming(username, site, startTime, endTime, shiftNumber);
                        } else {
                            // Insert new record
                            ShiftTiming newShift = new ShiftTiming();
                            newShift.setUsername(username);
                            newShift.setSite(site);
                            newShift.setStartTime(startTime);
                            newShift.setEndTime(endTime);
                            newShift.setShiftNumber(shiftNumber);
                            shiftTimingRepository.save(newShift);
                        }
                    }
                }
            }
        }
    }
}
