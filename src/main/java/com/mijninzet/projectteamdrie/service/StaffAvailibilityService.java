package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffAvailibilityService {
    @Autowired
    private StaffAvailibilityRepository staffAvailibilityRepository;

    public List<StaffAvailability> getAllStaffAvailibility(int userId){
        ArrayList<StaffAvailability> staffAvailabilities=new ArrayList<>();
        staffAvailibilityRepository.findAll()
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;

    }

    public void addStaffAvailibility(StaffAvailability sa){
        staffAvailibilityRepository.save(sa);
    }
}

