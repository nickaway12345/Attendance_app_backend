package com.example.attendance_calculator.repository;

import com.example.attendance_calculator.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Optional<Site> findBySiteId(String siteId);
}

