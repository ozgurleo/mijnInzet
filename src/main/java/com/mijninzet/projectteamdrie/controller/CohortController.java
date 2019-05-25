package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Controller
@RequestMapping(value="/cohort")


public class CohortController {

    @Autowired
    CohortRepository cohortRepository;

    public static final int DAYS_IN_WEEK=7;

    @RequestMapping(value="/createCohort", method = RequestMethod.GET)
    public String getAllCohorts(Model model, Cohort cohort){
        List<Cohort> cohorts = cohortRepository.findAll();
        Cohort cohort1 = new Cohort();
        model.addAttribute("allCohorts", cohorts);
        return "createCohort";
    }

    @RequestMapping(value="/createCohort/new", method= RequestMethod.POST)
    public String createCohort(@ModelAttribute ("cohort") Cohort cohort, Model model){
        model.addAttribute("cohortId", cohort.getCohortId());
        model.addAttribute("dateBegin", cohort.getBeginDate());
        model.addAttribute("dateEnd", cohort.getEndDate());
        System.out.println("uit model addattribute komt als begindate " + cohort.getBeginDate() + " en enddate " + cohort.getEndDate());
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
