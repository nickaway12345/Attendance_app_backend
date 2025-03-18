package com.example.attendance_calculator.repository;


import com.example.attendance_calculator.model.SiteUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUsers, String> {
    SiteUsers findByUsername(String username);
}

