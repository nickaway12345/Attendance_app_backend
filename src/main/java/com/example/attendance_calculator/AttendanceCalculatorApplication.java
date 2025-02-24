package com.example.attendance_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.attendance_calculator.model")
@EnableJpaRepositories(basePackages = "com.example.attendance_calculator.repository")
public class AttendanceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceCalculatorApplication.class, args);
	}

}
