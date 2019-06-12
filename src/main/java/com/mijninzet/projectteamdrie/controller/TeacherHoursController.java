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
        model.addAttribute("cohortScheduleList", cohortScheduleList);
        model.addAttribute("cohorts", cohorts);

        return "teacherFTE";
    }

    @RequestMapping(value = "teacherSchedule", method = RequestMethod.GET)
    public String getTeacherSchedule(@RequestParam("cohortId") Integer cohortId, User user, Model model) {
        User currentUser = userService.findById(user.getCurrentUserId());
        List<CohortSchedule> cohortScheduleListByCohortAndUser = cohortScheduleRepository.getCohortScheduleByCohort_CohortIdAndUser_Id(cohortId, currentUser.getId());
        List<CohortSchedule> nrOfMondays = cohortScheduleRepository.getAllByDay("Monday");
        List<CohortSchedule> mondayMorningList = cohortScheduleRepository.getAllByDayAndDaypart("Monday", "morning");
        List<CohortSchedule> mondayAfternoonList = cohortScheduleRepository.getAllByDayAndDaypart("Monday", "afternoon");
        List<CohortSchedule> tuesdayMorningList = cohortScheduleRepository.getAllByDayAndDaypart("Tuesday", "morning");
        List<CohortSchedule> tuesdayAfternoonlist = cohortScheduleRepository.getAllByDayAndDaypart("Tuesday", "afternoon");
        List<CohortSchedule> wednesdayMorningList = cohortScheduleRepository.getAllByDayAndDaypart("Wednesday", "morning");
        List<CohortSchedule> wednesdayAfternoonList = cohortScheduleRepository.getAllByDayAndDaypart("Wednesday", "afternoon");
        List<CohortSchedule> thursdayMorningList = cohortScheduleRepository.getAllByDayAndDaypart("Thursday", "morning");
        List<CohortSchedule> thursdayAfternoonList = cohortScheduleRepository.getAllByDayAndDaypart("Thursday", "afternoon");
        model.addAttribute("cohortScheduleList", cohortScheduleListByCohortAndUser);
        model.addAttribute("maandag", nrOfMondays);
        model.addAttribute("maandagmorgen", mondayMorningList);
        model.addAttribute("maandagmiddag", mondayAfternoonList);


//       List<TeacherSchedule> teacherSchedule = teacherScheduleRepository.getAllByCohort_CohortId(cohortId);
//        getTeacherFte(currentUser, model);
//
//        Long numberOfWeeksInCohort = cohortService.getWeeksInCohort(cohortId);
//
//      int intNumberOfWeeksInCohort =  numberOfWeeksInCohort.intValue();
//
//      TeacherSchedule[] teacherSchedules = new TeacherSchedule[intNumberOfWeeksInCohort];
//
//
//        for (CohortSchedule cohortschedule : cohortScheduleListByCohortAndUser) {
//            if (cohortschedule.getDay().equalsIgnoreCase("Monday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                for(int i =0; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setMaandagOchtend(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Monday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                for(int i = 0 ; i< teacherSchedules.length; i++) {
//                    teacherSchedules[i].setMaandagMiddag(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Tuesday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                for(int i = 0 ; i< teacherSchedules.length; i++) {
//                    teacherSchedules[i].setDinsdagOchtend(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Tuesday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setDinsdagMiddag(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Wednesday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setWoensdagOchtend(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Wednesday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setWoensdagMiddag(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Thursday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setDonderdagOchtend(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Thursday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setDonderdagMiddag(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Friday") && cohortschedule.getDaypart().equalsIgnoreCase("morning")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setVrijdagOchtend(cohortschedule.getSubject().getSubjectName());
//                }
//            } else if (cohortschedule.getDay().equalsIgnoreCase("Friday") && cohortschedule.getDaypart().equalsIgnoreCase("afternoon")) {
//                for(int i = 0 ; i<teacherSchedules.length; i++) {
//                    teacherSchedules[i].setVrijdagMiddag(cohortschedule.getSubject().getSubjectName());
//                }
//            }
//        }
//        model.addAttribute("teacherSchedules", teacherSchedules);
//
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
