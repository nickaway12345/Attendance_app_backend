package com.example.attendance_calculator.model;

public class RegularizeDTO {

    private String empId;
    private String date;
    private String newInTime;
    private String newOutTime;
    private String locationIn;
    private String locationOut;
    private Double totalHours;
    private String approvalStatus;

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewInTime() {
        return newInTime;
    }

    public void setNewInTime(String newInTime) {
        this.newInTime = newInTime;
    }

    public String getNewOutTime() {
        return newOutTime;
    }

    public void setNewOutTime(String newOutTime) {
        this.newOutTime = newOutTime;
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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }
}
