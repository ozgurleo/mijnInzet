package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;
    @Autowired
    StaffAvailibilityRepository availibilityRepository;


    @RequestMapping(value="/schedule")
    public String showSchedule (){
        return "schedule";
    }

    @RequestMapping("schedule/{id}")
    public List<StaffAvailability> getAllSchedule(@PathVariable Integer id){
        return staffAvailibilityService.getAllStaffAvailibility(id);
    }

    @PostMapping("schedule/{userId}/new")
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId){
        sa.setUser(new Teacher(userId));
        staffAvailibilityService.addStaffAvailibility(sa);

    }
}

