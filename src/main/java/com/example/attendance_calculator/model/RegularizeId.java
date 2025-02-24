package com.example.attendance_calculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegularizeId implements Serializable {

    @Column(name = "emp_id")
    @JsonProperty("empId")
    private String empId;

    @Column(name = "date")
    @JsonProperty("date")
    private String date;

    public RegularizeId(String empId, String date) {
        this.empId = empId;
        this.date = date;
    }

    public RegularizeId() {
    }

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

    // Equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegularizeId that = (RegularizeId) o;
        return Objects.equals(empId, that.empId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, date);
    }
}
