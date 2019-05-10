package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String getAllschedule(Model model,@PathVariable Integer id ){
        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibility(id);
        List<StaffAvailability>maandag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))
                .collect(Collectors.toList());
        List<StaffAvailability>disndag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))
                .collect(Collectors.toList());
        List<StaffAvailability>woensdag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))
                .collect(Collectors.toList());
        List<StaffAvailability>donderdag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))
                .collect(Collectors.toList());
        List<StaffAvailability>vrijdag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))
                .collect(Collectors.toList());

        model.addAttribute("maandag",maandag);
        model.addAttribute("dinsdag",disndag);
        model.addAttribute("woensdag",woensdag);
        model.addAttribute("donderdag",donderdag);
        model.addAttribute("vrijdag",vrijdag);

        return "newschedule";
    }
    @PostMapping("schedule/{userId}/new")
    @ResponseBody
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
        sa.setUser(new Teacher(userId));
        staffAvailibilityService.addStaffAvailibility(sa);

    }
}