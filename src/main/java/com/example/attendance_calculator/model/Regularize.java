package com.example.attendance_calculator.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "regularize")
public class Regularize {

    @EmbeddedId
    @JsonProperty("id")
    private RegularizeId id;

    @Column(name = "new_in_time")
    @JsonProperty("inTime")
    private String inTime;

    @Column(name = "new_out_time")
    @JsonProperty("outTime")
    private String outTime;

    @Column(name = "old_in_time")
    @JsonProperty("oldinTime")
    private String oldinTime;

    @Column(name = "old_out_time")
    @JsonProperty("oldoutTime")
    private String oldoutTime;

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

    @Column(name = "approval")
    @JsonProperty("approval")
    private String approval;

    @Column(name = "approved_by")
    @JsonProperty("approved_by")
    private String approved_by;

    @Column(name = "approved_at")
    @JsonProperty("approved_at")
    private LocalDateTime approved_at;

    @Column(name = "reason")
    @JsonProperty("reason")
    private String reason;

    // Getters and Setters

    public RegularizeId getId() {
        return id;
    }

    public void setId(RegularizeId id) {
        this.id = id;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOldInTime() {
        return oldinTime;
    }

    public void setOldInTime(String oldinTime) {
        this.oldinTime = oldinTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getOldOutTime() {
        return oldoutTime;
    }

    public void setOldOutTime(String oldoutTime) {
        this.oldoutTime = oldoutTime;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public LocalDateTime getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(LocalDateTime approved_at) {
        this.approved_at = approved_at;
    }

    // Equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regularize that = (Regularize) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(inTime, that.inTime) &&
                Objects.equals(outTime, that.outTime) &&
                Objects.equals(oldoutTime, that.oldoutTime) &&
                Objects.equals(totalHours, that.totalHours) &&
                Objects.equals(location_in, that.location_in) &&
                Objects.equals(location_out, that.location_out) &&
                Objects.equals(day, that.day) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(punchInLat, that.punchInLat) &&
                Objects.equals(punchInLong, that.punchInLong) &&
                Objects.equals(punchOutLat, that.punchOutLat) &&
                Objects.equals(punchOutLong, that.punchOutLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inTime, outTime,oldinTime, oldoutTime, totalHours, location_in, location_out, day, punchInLat, punchInLong, punchOutLat, punchOutLong, reason);
    }


}
