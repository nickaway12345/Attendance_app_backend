package com.example.attendance_calculator.service;


import com.example.attendance_calculator.model.SiteUsers;
import com.example.attendance_calculator.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteUserService {

    @Autowired
    private SiteUserRepository siteUserRepository;

    public String getUserSite(String username) {
        SiteUsers user = siteUserRepository.findByUsername(username);
        return (user != null) ? user.getSite() : null;
    }
}

