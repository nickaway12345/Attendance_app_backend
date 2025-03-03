package com.example.attendance_calculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class Leave {
    @JsonProperty("start_date")
    private long startDate;

    @JsonProperty("end_date")
    private long endDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("leave_type")
    private String leaveType;

    @JsonProperty("username")
    private String username;

    // Getters and setters
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}