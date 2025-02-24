package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Attendance;
import com.example.attendance_calculator.model.AttendanceId;
import com.example.attendance_calculator.model.Regularize;
import com.example.attendance_calculator.model.RegularizeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegularizeRepository extends JpaRepository<Regularize, RegularizeId> {
    // Custom query if needed to find attendance records
    @Query("SELECT r FROM Regularize r WHERE r.id.empId = :empId AND r.approval = :approval")
    List<Regularize> findByEmpIdAndApproval(@Param("empId") String empId, @Param("approval") String approval);
}
