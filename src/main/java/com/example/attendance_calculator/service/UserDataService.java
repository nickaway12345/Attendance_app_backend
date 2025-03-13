package com.example.attendance_calculator.service;



import com.example.attendance_calculator.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserDataService {

    @Value("${api.userdata.url}")
    private String API_URL;

    @Value("${data.userDataFile.file}")
    private String FILE_PATH;

    @Scheduled(cron = "0 0 0/4 * * *")
    public void fetchAndStoreUserData() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();


            List<UserData> users = restTemplate.getForObject(API_URL, List.class);

            objectMapper.writeValue(new File(FILE_PATH), users);

            System.out.println("User data saved successfully in " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error fetching API: " + e.getMessage());
        }
    }
}

