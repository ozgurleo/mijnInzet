package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;
    @Autowired
    StaffAvailibilityRepository availibilityRepository;
    @Autowired
    private UserRepository userRepository;


    StaffAvailability sa = new StaffAvailability();

    @RequestMapping(value = "schedule", method = RequestMethod.GET)
    public String schedules(Model model, User user) {
        User currentuser = userRepository.findUserById(user.getCurrentUserId());
        StaffAvailability sa = new StaffAvailability();
        model.addAttribute("allStaffAvailibilities", availibilityRepository.findAll());
        model.addAttribute("userId", currentuser);
        model.addAttribute("id", availibilityRepository.findById(sa.getId()));
        System.out.println("requestmapping schedule get geeft als currentuser:" + currentuser);
        return "schedule";
    }

    @RequestMapping(value = "schedule", method = RequestMethod.POST)
    public String postSchedules(Model model) {
        model.addAttribute("staffavailibility", new StaffAvailability());
        model.addAttribute("allStaffAvailibilities", availibilityRepository.findAll());
        model.addAttribute("id", sa.getId());
        return "schedule";
    }

//    @RequestMapping("schedule/{id}")
//    public String getAllschedule(Model model, @PathVariable Integer id) {
//        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibility(id);
//        List<StaffAvailability> maandag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> dinsdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> woensdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> donderdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> vrijdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))
//                .collect(Collectors.toList());
//        StaffAvailability staffavailabilityId = availibilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Ongeldige id" + id)));
//
//        model.addAttribute("maandag", maandag);
//        model.addAttribute("dinsdag", dinsdag);
//        model.addAttribute("woensdag", woensdag);
//        model.addAttribute("donderdag", donderdag);
//        model.addAttribute("vrijdag", vrijdag);
//        model.addAttribute("staffavailabilityId", staffavailabilityId.getId());
//        model.addAttribute("staffavailibility", sa);
//        model.addAttribute("setId", availibilityRepository.getOne(id));
//        return "schedule";
//    }

    @RequestMapping("schedule/{cohort}")
    public String GetAllSchedulesByCohort(User user, Model model, @PathVariable String cohort) {
        User currentUser = userRepository.findUserById(user.getCurrentUserId());
        List<StaffAvailability> sa = availibilityRepository.findAllByUserIdAndCohort(currentUser.getId(), cohort);
        List<StaffAvailability> maandag = sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))
                .limit(3)
                .collect(Collectors.toList());
        List<StaffAvailability> dinsdag = sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))
                .limit(3)
                .collect(Collectors.toList());
        List<StaffAvailability> woensdag = sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))
                .limit(3)
                .collect(Collectors.toList());
        List<StaffAvailability> donderdag = sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))
                .limit(3)
                .collect(Collectors.toList());
        List<StaffAvailability> vrijdag = sa.stream()
                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))
                .limit(3)
                .collect(Collectors.toList());

        model.addAttribute("maandag", maandag);
        model.addAttribute("dinsdag", dinsdag);
        model.addAttribute("woensdag", woensdag);
        model.addAttribute("donderdag", donderdag);
        model.addAttribute("vrijdag", vrijdag);
        model.addAttribute("staffavailibility", sa);
        System.out.println("Uit requestmapping schedule/cohort komt cohort " + cohort);
        System.out.println("en uit requestmapping schedule/ cohort komt currentUser " + currentUser);


        return "schedule";
    }

//    @RequestMapping("schedule/{cohort}")
//    public String GetAllSchedulesByCohort(Model model, @PathVariable String cohort) {
//        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibilityByCohort(cohort);
//        List<StaffAvailability> maandag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Maandag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> dinsdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Dinsdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> woensdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Woensdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> donderdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Donderdag"))
//                .collect(Collectors.toList());
//        List<StaffAvailability> vrijdag = sa.stream()
//                .filter(staffAvailability -> staffAvailability.getDay().contains("Vrijdag"))
//                .collect(Collectors.toList());
//
//        model.addAttribute("maandag", maandag);
//        model.addAttribute("dinsdag", dinsdag);
//        model.addAttribute("woensdag", woensdag);
//        model.addAttribute("donderdag", donderdag);
//        model.addAttribute("vrijdag", vrijdag);
//        model.addAttribute("staffavailibility", sa);
//        return "schedule";
//    }

    @PostMapping("schedule/{userId}/new")
    @ResponseBody
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
        sa.setUser(new User(userId));
        staffAvailibilityService.addStaffAvailibility(sa);
    }

    @RequestMapping (value = "updateSchedule/{id}" , method=RequestMethod.GET)
    public String showUpdateScheduleForm(@PathVariable int id, Model model) {
        System.out.println(id);
        StaffAvailability sa1 = availibilityRepository.findById(id);
        model.addAttribute("staffavailibility", sa1);
        model.addAttribute("colorOption", sa1.getColorOption());
        model.addAttribute("id", sa1.getId());
        model.addAttribute("day", sa1.getDay());
        model.addAttribute("dayPart", sa1.getDayPart());
        model.addAttribute("user", sa1.getUser());
        model.addAttribute("cohort", sa1.getCohort());
        System.out.println("uit de updateSchedule/id GET methode komt coloroption " +sa1.getColorOption());
        System.out.println("uit de updateSchedule/id GET methode komt id " +sa1.getId());
        System.out.println("uit de updateSchedule/id GET methode komt daypart " +sa1.getDayPart());
        return "updateSchedule";
    }


    @PostMapping(value="/updatedSchedule")
    public String updateTimeSchedule (@ModelAttribute("staffavailibility") StaffAvailability sa) {
        System.out.println("====================== "+ sa.toString());
//        model.addAttribute("staffavailibility", sa);
//        model.addAttribute("id", sa.getId());
//        model.addAttribute("colorOption", sa.getColorOption());
//        model.addAttribute("day", sa.getDay());
//        model.addAttribute("dayPart", sa.getDayPart());
//        model.addAttribute("user", sa.getUser());
//        model.addAttribute("cohort", sa.getCohort());
        System.out.println("updatedSchedule/id post request geeft door " + sa.getColorOption());
        System.out.println("updatedSchedule/id post request geeft door als id " + sa.getId());
        System.out.println("updatedSchedule/id post request geeft door " + sa.getUser());
        System.out.println("updatedSchedule/id post request geeft door als SA " + sa.toString());
        availibilityRepository.save(sa);

        return "schedule";
    }




}