package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.*;
import com.mijninzet.projectteamdrie.model.entity.user.Role;
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
    @Autowired
    SubjectRepository subjectRepository;


    public TeacherSchedule getTeacherScheduleByWeeknr(int cohortId, int weeknr) {

        TeacherSchedule ts = new TeacherSchedule();


        List<CohortSchedule> cohortScheduleList = cohortScheduleRepository.getAllByWeeknr(weeknr);

        for (CohortSchedule cs : cohortScheduleList) {

            String dag = cs.getDay();
            String dagdeel = cs.getDaypart();


            switch (dag) {
                case "Monday":
                    switch (dagdeel) {
                        case "morning":
                            ts.setMaandagOchtend(cs.getSubject().getSubjectName());
                            break;
                        case "afternoon":
                            ts.setMaandagMiddag(cs.getSubject().getSubjectName());
                            break;
                    }
                case "Tuesday":
                    switch (dagdeel) {
                        case "morning":
                            ts.setDinsdagOchtend(cs.getSubject().getSubjectName());
                            break;
                        case "afternoon":
                            ts.setDinsdagMiddag(cs.getSubject().getSubjectName());
                            break;
                    }
                case "Wednesday":
                    switch (dagdeel) {
                        case "morning":
                            ts.setWoensdagOchtend(cs.getSubject().getSubjectName());
                            break;
                        case "afternoon":
                            ts.setWoensdagMiddag(cs.getSubject().getSubjectName());
                            break;
                    }
                case "Thursday":
                    switch (dagdeel) {
                        case "morning":
                            ts.setDonderdagOchtend(cs.getSubject().getSubjectName());
                            break;
                        case "afternoon":
                            ts.setDonderdagMiddag(cs.getSubject().getSubjectName());
                            break;
                    }
                case "Friday":
                    switch (dagdeel) {
                        case "morning":
                            ts.setVrijdagOchtend(cs.getSubject().getSubjectName());
                            break;
                        case "afternoon":
                            ts.setVrijdagMiddag(cs.getSubject().getSubjectName());
                            break;
                    }

            }
            ts.setCohortId(cohortId);
            ts.setWeeknr(weeknr);
            teacherScheduleRepository.save(ts);
        }

        return ts;
    }


    @GetMapping("teacherFTE")
    public String getTeacherFte(User user, Model model) {

        User currentUser = userService.findById(User.getCurrentUserId());
       // double availableHours = userServiceImp.calculateTotalAvailableHours(currentUser.getId());
        double userFTE = user.getFte();
        int availableHours = teacherHoursRepository.getHoursLeft(User.getCurrentUserId());
        List<Cohort> cohorts = cohortRepository.findAllByCohortIdAfter(0);
        List<Integer> weeknrs = cohortScheduleRepository.getDistinctWeeknumbers();
        List<Subject> subjects = subjectRepository.findAll();


        model.addAttribute("voornaam", currentUser.getName());
        model.addAttribute("achternaam", currentUser.getLastName());
        model.addAttribute("email", currentUser.getEmail());
        model.addAttribute("fte", userFTE);
        model.addAttribute("beschikbareUren", availableHours);
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("weeknrs", weeknrs);
        model.addAttribute("vakkenSelectAjax", subjects);


        return "teacherFTE";
    }

    @RequestMapping(value = "teacherSchedule/{cohortId}", method = RequestMethod.GET)
    public @ResponseBody List<Integer> getTeacherSchedule(@PathVariable Integer cohortId, User user, Model model) {

        getTeacherFte(user, model);
        System.out.println("teacherschedule/{cohortid} is aangeroepen");
        List<Integer> distinctWeeknrsFromCohort = cohortScheduleRepository.getDistinctWeeknumbersWhereCohortIdIs(cohortId);

//        model.addAttribute("weken", distinctWeeknrsFromCohort);
        System.out.println("Uit methode komt " + distinctWeeknrsFromCohort);


        return distinctWeeknrsFromCohort;
    }

    @RequestMapping(value = "teacherSchedule/week/{weeknr}/{cohortId}", method = RequestMethod.GET)
    public @ResponseBody List<CohortSchedule> getTeacherWeekSchedule(@PathVariable int weeknr, @PathVariable int cohortId, User user, Model model) {

        getTeacherFte(user, model);
        System.out.println("Uit mijn requestparam komt : "+weeknr + " en cohortId " + cohortId);
        List<CohortSchedule> cohortScheduleWeek= cohortScheduleRepository.getAllByCohort_CohortIdAndWeeknr(cohortId, weeknr);
        System.out.println("uit mijn cohortscheduleweek komt " + cohortScheduleWeek);

//        model.addAttribute("week",cohortScheduleWeek);

        return cohortScheduleWeek;
    }

    @RequestMapping(value="/subject/{subjectId}")
    public @ResponseBody Subject getSubject(@PathVariable Integer subjectId) {

        System.out.println("MIJN ajax methode is aangeroepen");
        Subject subject = new Subject();
        subject = subjectRepository.findSubjectBySubjectId(subjectId);
        System.out.println("IN SUBJECT ZIT  " +subject );
        return subject;

    }
}
