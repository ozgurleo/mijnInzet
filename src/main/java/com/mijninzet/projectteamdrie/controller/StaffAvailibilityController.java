package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.CohortService;
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

    @Autowired
    private CohortService cohortService;

    private List<StaffAvailability>schedule;
//
//    @RequestMapping("/list")
//    public String listAllStaffAvailibility(Model model){
//        int userId=UserSingleton.getInstance().getId();
//        schedule=staffAvailibilityService.findByUserId(userId);
//        model.addAttribute("schedule",schedule);
//        return ("schedule/list-schedule");
//    }


    @RequestMapping("/list/{cohort}")
    public String listStaffAvailibilityByCohort(Model model,@PathVariable int cohort){
        int userId=UserSingleton.getInstance().getId();
        schedule=staffAvailibilityService.findStaffAvailibilityByUseridAndCohort(userId,cohort);
        model.addAttribute("schedule",schedule);
        return ("schedule/list-schedule");
    }

    @GetMapping("/addSchedule")
    public String addSchedule(Model model){
        StaffAvailability theSA= new StaffAvailability();
//        List<Cohort>cohorts=cohortService.findAll();
        model.addAttribute("schedule", theSA);
//        model.addAttribute("cohorts",cohorts);
        return "schedule/schedule-form";

    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedule") StaffAvailability sa){
        staffAvailibilityService.addStaffAvailibility(sa);
        return ("redirect:/schedule/listCohort");
    }

    @GetMapping("/updateSchedule")
    public String updateSchedule(@RequestParam("scheduleId") int theId, Model model){
        StaffAvailability sa =staffAvailibilityService.findById(theId);
        model.addAttribute("sa",sa);
        return "schedule/schedule-form";
    }

    @RequestMapping("/listCohort")
    public String listCohort(Model model){
        List<Cohort>cohorts=cohortService.findAll();
        model.addAttribute("cohorts", cohorts);
        return ("schedule/schedule-cohortlist");
    }


}