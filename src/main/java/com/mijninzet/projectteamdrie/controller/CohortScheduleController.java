package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Controller
public class CohortScheduleController {

    @Autowired
    CohortScheduleRepository cohortScheduleRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SubjectRepository subjectRepo;

    public static final int DAYS_IN_WEEK=7;



    //hier worden het aantal weken in een cohort bepaalt
    public int getNumberOfCohortWeeks(LocalDate beginDate, LocalDate endDate){
        int cohortWeeks=0;
        //get nr off days in a cohort
        long days = ChronoUnit.DAYS.between(beginDate, endDate);

        // convert nr of days into whole weeks (ROUND UP)
        if(days%DAYS_IN_WEEK>0){
            cohortWeeks=(int) (days/DAYS_IN_WEEK) + 1;
        }else{
            cohortWeeks=(int) (days/DAYS_IN_WEEK);        }

        System.out.println(" getNumberOfCohortWeeks method is aangeroepen in de taskController");
        System.out.println("Aantal DAGEN : " + days + "---> zijn in totaal " + cohortWeeks + " cohortWEKEN");
        return cohortWeeks;
    }


    @GetMapping(value="/generateCohortSchedule")
    public String generateCohortSchedule(Model model){

        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("cohorts",cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        return "generateCohortSchedule";
    }


    @PostMapping(value="/generateCohortSchedule")
    public String insertSchedule(HttpServletRequest request, Model model){
        System.out.println("POST METHOD IS AANGEROEPEN; DIT IS VOOR DE HET OPSLAAN VD COHORT");
        System.out.println(request.getContextPath());


        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("cohorts",cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        System.out.println("DIT IS NA HET OPHALEN VD LASTCOHORTSCHEDULE!!!");
        return "generateCohortSchedule";
    }

 //ORIGINEEL IS HIERONDER werkt tot aan save dan error
//    @PostMapping(value="/makeSchedule/cohort")
//    public String insertSchedule(@ModelAttribute CohortSchedule cohortschedule, Model model){
//        System.out.println("POST METHOD IS AANGEROEPEN; DIT IS VOOR DE HET OPSLAAN VD COHORT");
//        cohortScheduleRepo.save(cohortschedule);
//
//        System.out.println("DIT IS NA HET OPSLAAN VD COHORT!!!");
//        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
//        return "generateCohortSchedule";
//    }

}
