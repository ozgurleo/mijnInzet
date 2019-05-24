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


    @RequestMapping(value="/createCohort")
    public String createCohort() {
        return "createCohort";
    }


    public long getNumberOfCohortWeeks(LocalDate beginDate, LocalDate endDate){

        long weeksInYear = ChronoUnit.WEEKS.between(beginDate, endDate);

        return weeksInYear;
    }



}
