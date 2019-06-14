package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.model.entity.TeacherSchedule;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.*;
import com.mijninzet.projectteamdrie.service.CohortService;
import com.mijninzet.projectteamdrie.service.UserService;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherHoursController {


    @Autowired
    TeacherHoursRepository teacherHoursRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    CohortScheduleRepository cohortScheduleRepository;
    @Autowired
    CohortScheduleController cohortScheduleController;
    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    CohortService cohortService;
    @Autowired
    TeacherScheduleRepository teacherScheduleRepository;

    @GetMapping("teacherFTE")
    public String getTeacherFte(User user, Model model) {

        User currentUser = userService.findById(user.getCurrentUserId());
        double availableHours = userServiceImp.calculateTotalAvailableHours(currentUser.getId());
        double userFTE = user.getFte();
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepository.getCohortScheduleByUser_Id(currentUser.getId());
        List<Cohort> cohorts = cohortRepository.findAllByCohortIdAfter(10);


        model.addAttribute("voornaam", currentUser.getName());
        model.addAttribute("achternaam", currentUser.getLastName());
        model.addAttribute("fte", userFTE);
        model.addAttribute("beschikbareUren", availableHours);
        model.addAttribute("cohorts", cohorts);

        return "teacherFTE";
    }

    @RequestMapping(value = "teacherSchedule", method = RequestMethod.GET)
    public String getTeacherSchedule(@RequestParam("cohortId") Integer cohortId, User user, Model model) {

        int weeknr = 1;

        CohortSchedule mondaymorning = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId, weeknr, "morning", "Monday");
        CohortSchedule mondayafternoon = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "afternoon", "Monday");
        CohortSchedule tuesdaymorning = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "morning", "Tuesday");
        CohortSchedule tuesdayafternoon = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "afternoon", "Tuesday");
        CohortSchedule wednesdaymorning = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "morning", "Wednesday");
        CohortSchedule wednesdayafternoon = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "afternoon", "Wednesday");
        CohortSchedule thursdaymorning = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "morning", "Thursday");
        CohortSchedule thursdayafternoon = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "afternoon", "Thursday");
        CohortSchedule fridaymorning = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "morning", "Friday");
        CohortSchedule fridayafternoon = cohortScheduleRepository.findByCohort_CohortIdAndWeeknrAndDaypartAndDay(cohortId,weeknr, "afternoon", "Friday");


        model.addAttribute("mondaymorning", mondaymorning.getUser().getLastName());
        model.addAttribute("mondayafternoon", mondayafternoon.getSubject().getSubjectName());
        model.addAttribute("tuesdaymorning", tuesdaymorning.getSubject().getSubjectName());
        model.addAttribute("tuesdayafternoon", tuesdayafternoon.getSubject().getSubjectName());
        model.addAttribute("wednesdaymorning", wednesdaymorning.getSubject().getSubjectName());
        model.addAttribute("wednesdayafternoon", wednesdayafternoon.getSubject().getSubjectName());
        model.addAttribute("thursdaymorning", thursdaymorning.getSubject().getSubjectName());
        model.addAttribute("thursdayafternoon", thursdayafternoon.getSubject().getSubjectName());
        model.addAttribute("fridaymorning", fridaymorning.getSubject().getSubjectName());
        model.addAttribute("fridayafternoon", fridayafternoon.getSubject().getSubjectName());



        return "teacherFTE";
    }
//

//    @GetMapping("/teacherFTE/getSchedule")
//    public String getTeacherScheduleBasedOnDate(Model model, @RequestParam(value = "begindate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begindate, @RequestParam(value = "enddate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate) {
//
////        User currentUser = userService.findById(user.getCurrentUserId());
//        List<Cohort> cohorts = cohortRepository.findAll();
//        System.out.println("begindate staat als: " + begindate);
//        System.out.println("einddatum staat als" + enddate);
//
//        List<CohortSchedule> cohortScheduleListByBeginEndDate = cohortScheduleRepository.getCohortSchedulesByDateIsBetween(begindate, enddate);
//
//        for (CohortSchedule cohortschedule : cohortScheduleListByBeginEndDate) {
//            if (cohortschedule.getDay().equalsIgnoreCase("Monday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                model.addAttribute("Maandagochtend", cohortschedule.getSubject().getSubjectName());
//                System.out.println("maandagochtend "+ cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Monday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon"))
//            {
//                model.addAttribute("Maandagmiddag", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Tuesday") &&cohortschedule.getDaypart().equalsIgnoreCase("morning"))
//            {
//                model.addAttribute("Dinsdagochtend", cohortschedule.getSubject().getSubjectName());
//            }else if (cohortschedule.getDay().equalsIgnoreCase("Tuesday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                model.addAttribute("Dinsdagmiddag", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Wednesday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")){
//                model.addAttribute("Woensdagochtend", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Wednesday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                model.addAttribute("Woensdagmiddag", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Thursday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                model.addAttribute("Donderdagochtend", cohortschedule.getSubject().getSubjectName());
//            } else if(cohortschedule.getDay().equalsIgnoreCase("Thursday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")){
//                model.addAttribute("Donderdagmiddag", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Friday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                model.addAttribute("Vrijdagochtend", cohortschedule.getSubject().getSubjectName());
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Friday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                model.addAttribute("Vrijdagmiddag", cohortschedule.getSubject().getSubjectName());
//            }
//        }
//
//
//            return "teacherFTE";
//
//        }
}
