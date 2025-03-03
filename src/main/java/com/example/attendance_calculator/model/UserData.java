package com.example.attendance_calculator.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
    private String username;
    private String password;

    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("reporting_manager")
    private String reportingManager;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return first_name; }
    public void setFirstName(String firstName) { this.first_name = firstName; }

    public String getLastName() { return last_name; }
    public void setLastName(String lastName) { this.last_name = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getReportingManager() { return reportingManager; }
    public void setReportingManager(String reportingManager) { this.reportingManager = reportingManager; }
}

