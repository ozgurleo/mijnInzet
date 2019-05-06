package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;

    @RequestMapping("hello/schedule/{id}")
    public List<StaffAvailability> getAllSchedule(@PathVariable Integer id){
        return staffAvailibilityService.getAllStaffAvailibility(id);
    }

    @PostMapping("hello/schedule/{userId}/new")
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId){
        sa.setUser(new Teacher(userId));
        staffAvailibilityService.addStaffAvailibility(sa);


    }
}

