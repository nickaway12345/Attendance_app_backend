package com.example.attendance_calculator.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_service")
public class AttendanceService {

    @EmbeddedId
    @JsonProperty("id")
    private AttendanceServiceId id;

    @Column(name = "in_time")
    @JsonProperty("inTime")
    private String inTime;

    @Column(name = "out_time")
    @JsonProperty("outTime")
    private String outTime;

    @Column(name = "total_hours")
    @JsonProperty("totalHours")
    private double totalHours;

    @Column(name = "location_in")
    @JsonProperty("location_in")
    private String locationIn;

    @Column(name = "location_out")
    @JsonProperty("location_out")
    private String locationOut;

    @Column(name = "punch_in_lat")
    @JsonProperty("punch_in_lat")
    private double punchInLat;

    @Column(name = "punch_in_long")
    @JsonProperty("punch_in_long")
    private double punchInLong;

    @Column(name = "punch_out_lat")
    @JsonProperty("punch_out_lat")
    private double punchOutLat;

    @Column(name = "punch_out_long")
    @JsonProperty("punch_out_long")
    private double punchOutLong;


    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public String getLocationIn() {
        return locationIn;
    }

    public void setLocationIn(String locationIn) {
        this.locationIn = locationIn;
    }

    public String getLocationOut() {
        return locationOut;
    }

    public void setLocationOut(String locationOut) {
        this.locationOut = locationOut;
    }

    public double getPunchInLat() {
        return punchInLat;
    }

    public void setPunchInLat(double punchInLat) {
        this.punchInLat = punchInLat;
    }

    public double getPunchInLong() {
        return punchInLong;
    }

    public void setPunchInLong(double punchInLong) {
        this.punchInLong = punchInLong;
    }

    public double getPunchOutLat() {
        return punchOutLat;
    }

    public void setPunchOutLat(double punchOutLat) {
        this.punchOutLat = punchOutLat;
    }

    public double getPunchOutLong() {
        return punchOutLong;
    }

    public void setPunchOutLong(double punchOutLong) {
        this.punchOutLong = punchOutLong;
    }


    public AttendanceServiceId getId() {
        return id;
    }

    public void setId(AttendanceServiceId id) {
        this.id = id;
    }
}

