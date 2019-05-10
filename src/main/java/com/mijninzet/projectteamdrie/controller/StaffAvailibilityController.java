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
import org.springframework.web.servlet.ModelAndView;

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
    public String schedulesFromCohort(Model model) {
        model.addAttribute("staffavailibility", new StaffAvailability());
        return "schedule";
    }
    @RequestMapping("schedule/{id}")
    public String getAllschedule(Model model,@PathVariable Integer id ){
        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibility(id);
        List<StaffAvailability>maandag=sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))
                .collect(Collectors.toList());
        List<StaffAvailability>dinsdag=sa.stream()
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
        model.addAttribute("dinsdag",dinsdag);
        model.addAttribute("woensdag",woensdag);
        model.addAttribute("donderdag",donderdag);
        model.addAttribute("vrijdag",vrijdag);


        return "schedule";
    }

    @PostMapping("schedule/{userId}/new")
    @ResponseBody
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
        sa.setUser(new Teacher(userId));
        staffAvailibilityService.addStaffAvailibility(sa);

    }
////
////    @PostMapping("schedule")
////    public String selectCohort(@ModelAttribute StaffAvailability staffavailibility) {
////        staffavailibility.getCohort();
////        return "schedule";
////    }
//
//    @PostMapping("schedule/{userId}/new")
//    @ResponseBody
//    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
//        sa.setUser(new Teacher(userId));
//   //     staffAvailibilityService.addStaffAvailibility(sa);
//
////    }
////    @RequestMapping(value = "schedule", method = RequestMethod.POST)
////    public void addNewSchedule() {
////        staffAvailibilityService.addStaffAvailibility(2,"2", "maandag",  "rood", "middag");
////    }
////
////    @RequestMapping(value ="schedule", method = RequestMethod.PUT)
////    public List<StaffAvailability> createNewListStaffAvailability(){
////        return Arrays.asList(
////            new StaffAvailability(3, "maandag", "middag", "rood", "2", 2),
////        new StaffAvailability(4, "maandag", "middag", "rood", "2", 2)
////
////        );
//    }


}