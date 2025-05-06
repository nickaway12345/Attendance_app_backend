package com.example.attendance_calculator.service;

import com.example.attendance_calculator.model.Admin;
import com.example.attendance_calculator.model.OfficeCoordinate;
import com.example.attendance_calculator.repository.AdminRepository;
import com.example.attendance_calculator.repository.OfficeCoordinateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeCoordinateService {

    private static final Logger log = LoggerFactory.getLogger(OfficeCoordinateService.class);
    private final OfficeCoordinateRepository coordinateRepository;
    private final AdminRepository adminRepository;

    public OfficeCoordinateService(OfficeCoordinateRepository coordinateRepository,
                                   AdminRepository adminRepository) {
        this.coordinateRepository = coordinateRepository;
        this.adminRepository = adminRepository;
    }

    public List<OfficeCoordinate> getAllCoordinates() {
        return coordinateRepository.findAll();
    }

    public boolean saveOfficeCoordinate(String empId,
                                        String locationName,
                                        double latitude,
                                        double longitude) {
//        Optional<Admin> admin = adminRepository.findByUsername(empId);

            OfficeCoordinate coordinate = new OfficeCoordinate();
            coordinate.setLocationName(locationName);
            coordinate.setLatitude(latitude);
            coordinate.setLongitude(longitude);
        try {
            coordinateRepository.save(coordinate);
            return true;
        }catch(Exception e){
            log.error(String.valueOf(e));
            return false;
        }



    }

    public boolean deleteCoordinate(Long id) {
        if (coordinateRepository.existsById(id)) {
            coordinateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
