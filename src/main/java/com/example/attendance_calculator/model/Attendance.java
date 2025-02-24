package com.example.attendance_calculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "attendance")
public class Attendance {

    @EmbeddedId
    @JsonProperty("id")
    private AttendanceId id;

    @Column(name = "in_time")
    @JsonProperty("inTime")
    private String inTime;

    @Column(name = "out_time")
    @JsonProperty("outTime")
    private String outTime;

    @Column(name = "total_hours")
    @JsonProperty("totalHours")
    private Double totalHours;

    @Column(name = "location_in")
    @JsonProperty("location_in")
    private String location_in;

    @Column(name = "location_out")
    @JsonProperty("location_out")
    private String location_out;

    @Column(name = "day")
    @JsonProperty("day")
    private String day;

    @Column(name = "punch_in_lat")
    @JsonProperty("punch_in_lat")
    private Double punchInLat;

    @Column(name = "punch_in_long")
    @JsonProperty("punch_in_long")
    private Double punchInLong;

    @Column(name = "punch_out_lat")
    @JsonProperty("punch_out_lat")
    private Double punchOutLat;

    @Column(name = "punch_out_long")
    @JsonProperty("punch_out_long")
    private Double punchOutLong;



    // Getters and Setters

    public AttendanceId getId() {
        return id;
    }

    public void setId(AttendanceId id) {
        this.id = id;
    }

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

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getLocationIn() {
        return location_in;
    }

    public void setLocationIn(String location_in) {
        this.location_in = location_in;
    }

    public String getLocationOut() {
        return location_out;
    }

    public void setLocationOut(String location_out) {
        this.location_out = location_out;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getPunchInLat() {
        return punchInLat;
    }

    public void setPunchInLat(Double punchInLat) {
        this.punchInLat = punchInLat;
    }

    public Double getPunchInLong() {
        return punchInLong;
    }

    public void setPunchInLong(Double punchInLong) {
        this.punchInLong = punchInLong;
    }

    public Double getPunchOutLat() {
        return punchOutLat;
    }

    public void setPunchOutLat(Double punchOutLat) {
        this.punchOutLat = punchOutLat;
    }

    public Double getPunchOutLong() {
        return punchOutLong;
    }

    public void setPunchOutLong(Double punchOutLong) {
        this.punchOutLong = punchOutLong;
    }

    // Equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(inTime, that.inTime) &&
                Objects.equals(outTime, that.outTime) &&
                Objects.equals(totalHours, that.totalHours) &&
                Objects.equals(location_in, that.location_in) &&
                Objects.equals(location_out, that.location_out) &&
                Objects.equals(day, that.day) &&
                Objects.equals(punchInLat, that.punchInLat) &&
                Objects.equals(punchInLong, that.punchInLong) &&
                Objects.equals(punchOutLat, that.punchOutLat) &&
                Objects.equals(punchOutLong, that.punchOutLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inTime, outTime, totalHours, location_in, location_out, day, punchInLat, punchInLong, punchOutLat, punchOutLong);
    }


}
