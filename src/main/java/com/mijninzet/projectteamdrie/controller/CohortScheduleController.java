package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Controller
public class CohortScheduleController {

    @Autowired
    CohortScheduleRepository cohortScheduleRepo;

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
        model.addAttribute("cohortschedule", cohortScheduleRepo.findAll());
    return "generateCohortSchedule";
    }


}
