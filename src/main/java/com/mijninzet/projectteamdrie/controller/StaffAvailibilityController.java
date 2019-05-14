package com.mijninzet.projectteamdrie.controller;



import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;

//import com.mijninzet.projectteamdrie.model.entity.user.Teacher;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;

import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;

//import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;



import javax.management.modelmbean.ModelMBeanAttributeInfo;

import javax.validation.Valid;

import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;



@Controller

public class StaffAvailibilityController {

    @Autowired

    private StaffAvailibilityService staffAvailibilityService;

    @Autowired

    StaffAvailibilityRepository availibilityRepository;





    @RequestMapping(value = "schedule")

    public String schedules(Model model) {

        model.addAttribute("staffavailibility", new StaffAvailability());

        return "schedule";

    }



    @RequestMapping("schedule/{id}")

    public String getAllschedule(Model model, @PathVariable Integer id) {

        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibility(id);

        List<StaffAvailability> maandag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))

                .collect(Collectors.toList());

        List<StaffAvailability> dinsdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> woensdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> donderdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> vrijdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))

                .collect(Collectors.toList());



        model.addAttribute("maandag", maandag);

        model.addAttribute("dinsdag", dinsdag);

        model.addAttribute("woensdag", woensdag);

        model.addAttribute("donderdag", donderdag);

        model.addAttribute("vrijdag", vrijdag);



        return "schedule";

    }



    @RequestMapping("schedule/{id}/{cohort}")

    public String GetAllSchedulesByIdAndCohort(Model model, @PathVariable Integer id, @PathVariable String cohort) {

        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibilityByIdAndCohort(id, cohort);

        List<StaffAvailability> maandag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))

                .collect(Collectors.toList());

        List<StaffAvailability> dinsdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> woensdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> donderdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))

                .collect(Collectors.toList());

        List<StaffAvailability> vrijdag = sa.stream()

                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))

                .collect(Collectors.toList());



        model.addAttribute("maandag", maandag);

        model.addAttribute("dinsdag", dinsdag);

        model.addAttribute("woensdag", woensdag);

        model.addAttribute("donderdag", donderdag);

        model.addAttribute("vrijdag", vrijdag);



        return "schedule";

    }



    @PostMapping("schedule/{userId}/new")

    @ResponseBody

    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {

        sa.setUser(new User(userId));

        staffAvailibilityService.addStaffAvailibility(sa);

    }



    @GetMapping("updateSchedule/{id}")

    public String showUpdateScheduleForm(@PathVariable(value = "id", required = false)int id, Model model){

        StaffAvailability sa = availibilityRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("Ongeldige id" +  id)));

        model.addAttribute("staffavailibility", sa);

        return "updateSchedule";

    }





    @PostMapping("updateSchedule/{id}")

    public String updateScheduleFromId(@Valid StaffAvailability sa , @PathVariable int id, Model model){

        availibilityRepository.save(sa);

        model.addAttribute("staffavailibility", availibilityRepository.findAll());

        return "schedule";



    }



}