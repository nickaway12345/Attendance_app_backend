package com.example.attendance_calculator.service;
import com.example.attendance_calculator.model.Regularization;
import com.example.attendance_calculator.repository.RegularizationServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularizationService {

    @Autowired
    private RegularizationServiceRepository repository;

    public Regularization submitRegularization(Regularization regularization) {
        return repository.save(regularization);
    }
}

