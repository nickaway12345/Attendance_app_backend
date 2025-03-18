package com.example.attendance_calculator.controller;


import com.example.attendance_calculator.model.Site;
import com.example.attendance_calculator.repository.SiteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    private final SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    // Fetch site coordinates by siteId
    @GetMapping("/{siteId}/coordinates")
    public ResponseEntity<Map<String, Double>> getSiteCoordinates(@PathVariable String siteId) {
        Optional<Site> site = siteRepository.findBySiteId(siteId);
        return site.map(value -> ResponseEntity.ok(Map.of(
                        "latitude", value.getLatitude(),
                        "longitude", value.getLongitude())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save new site with coordinates
    @PostMapping("/add")
    public ResponseEntity<Site> addSite(@RequestBody Site site) {
        return ResponseEntity.ok(siteRepository.save(site));
    }
}
