package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.User;
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
        staffAvailibilityRepository.findByUserId(userId);
        return staffAvailabilities;

    }

    public void addStaffAvailibility( String cohort, String day, String color_option, String day_part){
        StaffAvailability sa = new StaffAvailability();
        sa.setCohort(cohort);
        sa.setDay(day);
        sa.setColorOption(color_option);
        sa.setDayPart(day_part);
        staffAvailibilityRepository.save(sa);

    }
}

