package com.mijninzet.projectteamdrie.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Controller
@RequestMapping(value="/cohort")

public class CohortController {
public static final int DAYS_IN_WEEK=7;

    @RequestMapping(value="/createCohort")
    public String createCohort() {
        return "createCohort";
    }

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



}
