//package com.example.attendance_calculator.controller;
//
//import com.example.attendance_calculator.model.OfficeCoordinate;
//import com.example.attendance_calculator.service.OfficeCoordinateService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/locations/office-coordinates")
//public class OfficeCoordinateController {
//
//    private final OfficeCoordinateService service;
//
//    public OfficeCoordinateController(OfficeCoordinateService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<OfficeCoordinate> getOfficeCoordinates() {
//        return service.getAllCoordinates();
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addOfficeCoordinate(@RequestBody Map<String, Object> payload) {
//        String empId = (String) payload.get("empId");
//        Double latitude = (Double) payload.get("latitude");
//        Double longitude = (Double) payload.get("longitude");
//
//        if (empId == null || latitude == null || longitude == null) {
//            return ResponseEntity.badRequest().body("Missing required fields.");
//        }
//
//        boolean isSaved = service.saveOfficeCoordinate(empId, latitude, longitude);
//        if (isSaved) {
//            return ResponseEntity.ok("Office location added successfully.");
//        } else {
//            return ResponseEntity.status(403).body("Permission denied. Only admins can add locations.");
//        }
//    }
//}

package com.example.attendance_calculator.controller;

import com.example.attendance_calculator.model.OfficeCoordinate;
import com.example.attendance_calculator.service.OfficeCoordinateService;
import com.example.attendance_calculator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/locations")
public class OfficeCoordinateController {

    private final OfficeCoordinateService service;

    private final UserService userService;

    public OfficeCoordinateController(OfficeCoordinateService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/office-coordinates")
    public List<Map<String,Object>> getLocations() {
        // map entity → simple DTO so JSON field names match your Dart
        List<Map<String,Object>> out = new ArrayList<>();
        for (OfficeCoordinate c : service.getAllCoordinates()) {
            Map<String,Object> m = new HashMap<>();
            m.put("id",            c.getId());
            m.put("locationName",  c.getLocationName());
            m.put("latitude",      c.getLatitude());
            m.put("longitude",     c.getLongitude());
            out.add(m);
        }
        return out;
    }

    @PostMapping("/office-coordinates")
    public ResponseEntity<?> addLocation(@RequestBody Map<String, Object> payload) {
        String empId       = (String) payload.get("empId");
        String locationName = (String) payload.get("locationName");
        Double latitude    = (Double) payload.get("latitude");
        Double longitude   = (Double) payload.get("longitude");

        if (empId == null || locationName == null || latitude == null || longitude == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Missing required fields: empId, locationName, latitude, longitude.");
        }

        boolean saved = service.saveOfficeCoordinate(empId, locationName, latitude, longitude);
        if (saved) {
            return ResponseEntity.ok("Office location added successfully.");
        } else {
            return ResponseEntity
                    .status(403)
                    .body("Permission denied. Only admins can add locations.");
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        boolean deleted = service.deleteCoordinate(id);
        if (deleted) {
            return ResponseEntity.ok("Location deleted successfully.");
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Location with id " + id + " not found.");
        }
    }

    @GetMapping("/getadmin")
    public ResponseEntity<Map<String,Object>> checkAdmin(
            @RequestParam("empId") String empId) {

        boolean isAdmin = userService.isAdmin(empId);
        // returns { "isAdmin": true } or { "isAdmin": false }
        return ResponseEntity.ok(
                Collections.singletonMap("isAdmin", isAdmin)
        );
    }
}
