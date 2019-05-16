package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffAvailibilityService {
    @Autowired
    private StaffAvailibilityRepository staffAvailibilityRepository;

    private List<StaffAvailability> staffAvailabilities;

    public List<StaffAvailability> getAllStaffAvailibility(int userId){
        ArrayList<StaffAvailability> staffAvailabilities=new ArrayList<>();
        staffAvailibilityRepository.findByUserId(userId)
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;

    }

    public List<StaffAvailability> getAllStaffAvailibilityByIdAndCohort(int userId, String cohort){
        ArrayList<StaffAvailability> staffAvailabilities = new ArrayList<>();
        staffAvailibilityRepository.findAllByUserIdAndCohort(userId, cohort)
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;
    }

    public void addStaffAvailibility(StaffAvailability sa){
        staffAvailibilityRepository.save(sa);
    }

    public StaffAvailability updateStaffAvailability(int id, String cohort, String color, String day, String daypart){
            for(StaffAvailability sa: staffAvailabilities){
                if(sa.getId()==id){
                    int saIndex=staffAvailabilities.indexOf(sa);
                    sa.setColorOption(color);
                    sa.setDayPart(daypart);
                    sa.setDay(day);
                    sa.setCohort(cohort);
                    staffAvailabilities.set(saIndex, sa);
                    return sa;
                }
            }
        return null;

    }
    public StaffAvailability updateStaffAvailability(int id){
        for(StaffAvailability sa: staffAvailabilities){
            if(sa.getId()==id){
                int saIndex=staffAvailabilities.indexOf(sa);
                staffAvailabilities.set(saIndex, sa);
                return sa;
            }
        }
        return null;

    }

}

