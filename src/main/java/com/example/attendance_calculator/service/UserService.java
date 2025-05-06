package com.example.attendance_calculator.service;

import com.example.attendance_calculator.DjangoPasswordHasher;
import com.example.attendance_calculator.dto.LoginRequest;
import com.example.attendance_calculator.model.User;
import com.example.attendance_calculator.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Value("${data.userDataFile.file}")
    private String filePath;

    /**
     * Validates user credentials and returns the corresponding User object if successful.
     *
     * @param loginRequest The login request containing username and password.
     * @return The User object if credentials are valid, otherwise null.
     */
    public User validateCredentials(LoginRequest loginRequest) {
        try {
            // Read the list of users from the JSON file
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(
                    Paths.get(filePath).toFile(),
                    mapper.getTypeFactory().constructCollectionType(List.class, User.class)
            );

            // Iterate through the users to find a match
            for (User user : users) {
                if (user.getUsername().equals(loginRequest.getUsername())){
                    // Verify the password
                    if (DjangoPasswordHasher.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
                        logger.info("User " + loginRequest.getUsername() + " authenticated successfully.");
                        return user; // Return the user if credentials are valid
                    } else {
                        logger.warning("Invalid password for user: " + loginRequest.getUsername());
                        return null; // Return null if password is invalid
                    }
                }
            }

            // If no matching user is found
            logger.warning("User not found: " + loginRequest.getUsername());
            return null;
        } catch (IOException e) {
            logger.severe("Error reading user data file: " + e.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserData> getUserDataByUsername(String username) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<UserData> users = mapper.readValue(
                    Paths.get(filePath).toFile(),
                    mapper.getTypeFactory().constructCollectionType(List.class, UserData.class)
            );

            return users.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .map(this::sanitizeUserData);  // Replace nulls with "unknown"

        } catch (IOException e) {
            logger.severe("Error reading user data file: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Replaces any null fields in UserData with "unknown".
     */
    private UserData sanitizeUserData(UserData user) {
        user.setPassword("unknown");
        if (user.getFirstName() == null) user.setFirstName("unknown");
        if (user.getLastName() == null) user.setLastName("unknown");
        if (user.getEmail() == null) user.setEmail("unknown");
        if (user.getReportingManager() == null) user.setReportingManager("unknown");
        if (user.getSite() == null) user.setSite("unknown");
        if (user.getGender() == null) user.setGender("unknown");
        if (user.getDoj() == null) user.setDoj("unknown");
        if (user.getDepartment() == null) user.setDepartment("unknown");
        if (user.getBdate() == null) user.setBdate("unknown");
        if (user.getPhone() == null) user.setPhone("unknown");
        if (user.getMediclaim() == null) user.setMediclaim("unknown");
        return user;
    }

    public boolean isAdmin(String username) {
        return getUserDataByUsername(username)
                .map(u -> "Admin".equalsIgnoreCase(u.getRole()))
                .orElse(false);
    }
}