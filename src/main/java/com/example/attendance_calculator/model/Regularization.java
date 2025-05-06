package com.example.attendance_calculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "regularization")
public class Regularization {
    @EmbeddedId
    private RegularizationId id;

    private LocalTime oldInTime;
    private LocalTime oldOutTime;
    private String oldTotalHours;

    @Column(nullable = false)
    private LocalTime newInTime;

    @Column(nullable = false)
    private LocalTime newOutTime;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column( length = 20)
    private String status;  // e.g., "Pending", "Approved", "Rejected"

    @Column(length = 100)
    private String approvedBy; // Name or ID of the approver

    // Getters and Setters
    public RegularizationId getId() {
        return id;
    }

    public void setId(RegularizationId id) {
        this.id = id;
    }

    public LocalTime getOldInTime() {
        return oldInTime;
    }

    public void setOldInTime(LocalTime oldInTime) {
        this.oldInTime = oldInTime;
    }

    public LocalTime getOldOutTime() {
        return oldOutTime;
    }

    public void setOldOutTime(LocalTime oldOutTime) {
        this.oldOutTime = oldOutTime;
    }

    public String getOldTotalHours() {
        return oldTotalHours;
    }

    public void setOldTotalHours(String oldTotalHours) {
        this.oldTotalHours = oldTotalHours;
    }

    public LocalTime getNewInTime() {
        return newInTime;
    }

    public void setNewInTime(LocalTime newInTime) {
        this.newInTime = newInTime;
    }

    public LocalTime getNewOutTime() {
        return newOutTime;
    }

    public void setNewOutTime(LocalTime newOutTime) {
        this.newOutTime = newOutTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
