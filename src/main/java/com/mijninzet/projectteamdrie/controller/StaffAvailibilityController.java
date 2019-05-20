package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.model.entity.user.CurrentUser;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.service.StaffAvailibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class StaffAvailibilityController {
    @Autowired
    private StaffAvailibilityService staffAvailibilityService;
    @Autowired
    StaffAvailibilityRepository availibilityRepository;
    StaffAvailability sa = new StaffAvailability();


    @RequestMapping(value = "schedule")
    public String schedules(Model model) {
        model.addAttribute("staffavailibility", new StaffAvailability());
        model.addAttribute("allStaffAvailibilities", availibilityRepository.findAll());
        model.addAttribute("id", sa.getId());
        model.addAttribute("cohort", availibilityRepository.findAllByCohort(sa.getCohort()));
        return "schedule";
    }
    @RequestMapping(value = "schedule", method=RequestMethod.POST)
    public String postSchedules(Model model) {
        model.addAttribute("staffavailibility", new StaffAvailability());
        model.addAttribute("allStaffAvailibilities", availibilityRepository.findAll());
        model.addAttribute("id", sa.getId());

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
        StaffAvailability staffavailabilityId = availibilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Ongeldige id" + id)));

        model.addAttribute("maandag", maandag);
        model.addAttribute("dinsdag", dinsdag);
        model.addAttribute("woensdag", woensdag);
        model.addAttribute("donderdag", donderdag);
        model.addAttribute("vrijdag", vrijdag);
        model.addAttribute("staffavailabilityId",staffavailabilityId.getId() );
        model.addAttribute("staffavailibility", sa);
        model.addAttribute("setId", availibilityRepository.getOne(id));

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
        model.addAttribute("staffavailibility", sa);


        return "schedule";
    }

    @RequestMapping("schedule/{cohort}")
    public String GetAllSchedulesByCohort(Model model, @PathVariable String cohort) {
        List<StaffAvailability> sa = staffAvailibilityService.getAllStaffAvailibilityByCohort(cohort);
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
        model.addAttribute("staffavailibility", sa);


        return "schedule";
    }

    @PostMapping("schedule/{userId}/new")
    @ResponseBody
    public void addStaffAvailiblity(@RequestBody StaffAvailability sa, @PathVariable int userId) {
        sa.setUser(new User(userId));
        staffAvailibilityService.addStaffAvailibility(sa);
    }

    @RequestMapping(value = "updateSchedule/{id}", method = RequestMethod.GET)
    public String showUpdateScheduleForm(@PathVariable Integer id, Model model) {
        StaffAvailability sa1 = availibilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Ongeldige id" + id)));
        model.addAttribute("staffavailibility", sa1);
        return "updateSchedule";
    }

    @RequestMapping(value="/update/timeschedule", method=RequestMethod.POST)
    public String updateTimeschedule(@ModelAttribute StaffAvailability sa, StaffAvailability sa1, Model model) {
        System.out.println("mijn methode is aangeroepen");

        model.addAttribute("id", sa.getId());
        model.addAttribute("cohort", sa.getCohort());
        model.addAttribute("day", sa.getDay());
        model.addAttribute("dayPart", sa.getDayPart());
        model.addAttribute("colorOption", sa.getColorOption());
        model.addAttribute("userId", sa.getUser());
        System.out.println("en geeft mee " + sa.getId() + " " + sa.getColorOption() + " " + sa.getUser());
        staffAvailibilityService.addStaffAvailibility(sa1);
        availibilityRepository.save(sa1);
        return "schedule";
    }

//    @PostMapping(value = "/updateSchedule/{cohort}/{day}/{dayPart}/{colorOption}")
//    public String insertTaskAppl(HttpServletRequest request, ModelMap model){
//        System.out.println("methode is aangeroepen!");
//        //get the data from httpservletRequest and put in variable
//        String id = request.getParameter("id");
//        String cohort=request.getParameter("cohort");
//        String day=request.getParameter("day");
//        String colorOption=request.getParameter("colorOption");
//        String dayPart = request.getParameter("dayPart");
//        //gebruik repository om de nieuwe object naar db weg te schrijven
//        availibilityRepository.save(new StaffAvailability(1, "day", "dayPart", "colorOption", "cohort",1));
//        return "/updateSchedule";
   //}


//    @PostMapping("/updateSchedule")
//    public void updateStaffAvailibilityById(@RequestBody StaffAvailability sa, Model model, @PathVariable int id){
//        model.addAttribute("staffavailability", sa);
//        model.addAttribute("cohort", sa.getCohort());
//        model.addAttribute("day", sa.getDay());
//        model.addAttribute("dayPart", sa.getDayPart());
//        model.addAttribute("colorOption", sa.getColorOption());
//        availibilityRepository.save(sa);
//        System.out.println("updateschedule" + sa.toString());
//        }



//    @RequestMapping(value="updateSchedule",  method=RequestMethod.GET)
//    public String updateScheduleFromId(Model model){
//       StaffAvailability sa = this.availibilityRepository.findById();
//       model.addAttribute("staffavailibility", sa);
//        return "updateSchedule";
//    }
}
