package com.example.attendance_calculator.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class AttendanceServiceId implements Serializable {

    @Column(name = "emp_id")
    @JsonProperty("empId")
    private String empId;

    @Column(name = "date")
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "shift_number")
    @JsonProperty("shift_number")
    private int shiftNumber;



    public AttendanceServiceId() {}


    @JsonCreator
    public AttendanceServiceId(
             String empId,
             LocalDate date,
             int shiftNumber
    ) {
        this.empId = empId;
        this.date = date;
        this.shiftNumber = shiftNumber;
    }



    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }
}

