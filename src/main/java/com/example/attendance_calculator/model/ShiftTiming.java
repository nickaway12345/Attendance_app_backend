package com.example.attendance_calculator.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shift_timings")
public class ShiftTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "site", nullable = false)
    private String site;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "shift_number")
    private Integer shiftNumber; // Add shift_number column

    // Constructors
    public ShiftTiming() {
    }

    public ShiftTiming(String username, String site, LocalDateTime startTime, LocalDateTime endTime, Integer shiftNumber) {
        this.username = username;
        this.site = site;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftNumber = shiftNumber; // Initialize shiftNumber
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(Integer shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    @Override
    public String toString() {
        return "ShiftTiming{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", site='" + site + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", shiftNumber=" + shiftNumber + // Include shiftNumber in toString
                '}';
    }
}