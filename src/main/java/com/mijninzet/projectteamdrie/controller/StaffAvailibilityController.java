package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;
    @Autowired
    StaffAvailibilityRepository availibilityRepository;


    @RequestMapping(value = "/schedule")

    public String showSchedule(Model model) {
        model.addAttribute("test", new StaffAvailability());
        return "schedule";
    }

    @RequestMapping("schedule/{id}")
    @ResponseBody
    public List<StaffAvailability> getAllSchedule(@PathVariable Integer id) {
        return staffAvailibilityService.getAllStaffAvailibility(id);
    }

    @PostMapping("schedule/{userId}/new")
    @ResponseBody
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
        sa.setUser(new Teacher(userId));
        //staffAvailibilityService.addStaffAvailibility(sa);

    }
    @RequestMapping(value = "schedule", method = RequestMethod.POST)
    public void addNewSchedule() {
        staffAvailibilityService.addStaffAvailibility(2,"1", "maandag",  "rood", "middag");
    }

    @RequestMapping(value = "schedule", method=RequestMethod.GET)
    public void getSchedule(){
        new StaffAvailability(4, "maandag", "middag", "red", "1", 2);
    }


}