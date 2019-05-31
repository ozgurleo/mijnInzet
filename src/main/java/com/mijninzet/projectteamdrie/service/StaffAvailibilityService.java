package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffAvailibilityService {
    @Autowired
    private StaffAvailibilityRepository staffAvailibilityRepository;

    private List<StaffAvailability> staffAvailabilities;
    private StaffAvailability staffAvailability;

    public List<StaffAvailability> getAll(){
        ArrayList<StaffAvailability> sa = new ArrayList<>();
        staffAvailibilityRepository.findAll()
                .forEach(sa::add);
        return sa;
    }

    public List<StaffAvailability> findStaffAvailibilityByUseridAndCohort(int userId, String cohort){
        List<StaffAvailability> staffAvailabilities=new ArrayList<>();
        staffAvailibilityRepository.findByUser_IdAndAndCohort(userId,cohort)
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;
    }

//    public List<StaffAvailability> getAllStaffAvailibility(int userId){
//        ArrayList<StaffAvailability> staffAvailabilities=new ArrayList<>();
//        staffAvailibilityRepository.findByUserId(userId)
//                .forEach(staffAvailabilities::add);
//        return staffAvailabilities;
//
//    }
//
//    public List<StaffAvailability> getAllStaffAvailibilityByIdAndCohort(int userId, int cohort){
//        ArrayList<StaffAvailability> staffAvailabilities = new ArrayList<>();
//        staffAvailibilityRepository.findAllByUserIdAndCohort(userId, cohort)
//                .forEach(staffAvailabilities::add);
//        return staffAvailabilities;
//    }
//
//    public List<StaffAvailability> getAllStaffAvailibilityByCohort(String cohort){
//        ArrayList<StaffAvailability> staffAvailabilities = new ArrayList<>();
//        staffAvailibilityRepository.findAllByCohort( cohort)
//                .forEach(staffAvailabilities::add);
//        return staffAvailabilities;
//    }
//
    public void addStaffAvailibility(StaffAvailability sa){
        staffAvailibilityRepository.save(sa);

    }
//
//    public void updateStaffAvailibility(StaffAvailability sa){
//        StaffAvailability sanieuw = staffAvailibilityRepository.findById(sa.getId());
//        staffAvailibilityRepository.save(sanieuw);
//    }


}

