package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;




@Controller
@RequestMapping("/schedule")

public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;

    private List<StaffAvailability>schedule;


    @RequestMapping("/list/{cohort}")
    public String listStaffAvailibility(Model model,@PathVariable String cohort){
        int userId=UserSingleton.getInstance().getId();
        schedule=staffAvailibilityService.findStaffAvailibilityByUseridAndCohort(userId,cohort);
        model.addAttribute("schedule",schedule);
        return ("schedule/list-schedule");
    }

    @GetMapping("/addSchedule")
    public String addSchedule(Model model){
        StaffAvailability theSA= new StaffAvailability();
        model.addAttribute("schedule", theSA);
        return "schedule/schedule-form";

    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedule") StaffAvailability sa){
        staffAvailibilityService.addStaffAvailibility(sa);
        //hier moet er automatisch cohort worden ingezetten!!!!
        return ("redirect:/schedule/list/16");
    }


}