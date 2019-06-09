package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;


@Controller
@RequestMapping(value="/cohort")


public class CohortController {

    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    UserRepository userRepository;

    public static final int DAYS_IN_WEEK=7;

    @RequestMapping(value="/createCohort", method = RequestMethod.GET)
    public String getAllCohorts(Model model){
        List<Cohort> cohorts = cohortRepository.findAll();
        Cohort cohort = new Cohort();
        model.addAttribute("allCohorts", cohorts);
        model.addAttribute("cohort", cohort);
        model.addAttribute("cohortId", cohort.getCohortId());
        model.addAttribute("beginDate", cohort.getBeginDate());
        model.addAttribute("endDate", cohort.getEndDate());
        return "createCohort";
    }

    @RequestMapping(value="/createCohort/new/", method= RequestMethod.POST)
    public String createCohort(@ModelAttribute ("cohort") Cohort cohort, Model model){

        boolean exists = cohortRepository.existsById(cohort.getCohortId());
        if(exists){
            return "createCohortError";
        }

        cohortRepository.save(cohort);
        return ("redirect:/cohort/createCohort");
    }

    //hier wordt het aantal weken in een cohort bepaalt
    public int getNumberOfCohortWeeks(LocalDate beginDate, LocalDate endDate){
        int cohortWeeks=0;
        //get nr off days in a cohort
        long days = ChronoUnit.DAYS.between(beginDate, endDate);

        // convert nr of days into whole weeks (ROUND UP)
        if(days%DAYS_IN_WEEK>0){
            cohortWeeks=(int) (days/DAYS_IN_WEEK) + 1;
        }else{
            cohortWeeks=(int) (days/DAYS_IN_WEEK);        }

        System.out.println(" getNumberOfCohortWeeks method is aangeroepen");
        System.out.println("Aantal DAGEN : " + days + "---> zijn in totaal " + cohortWeeks + " cohortWEKEN");
        return cohortWeeks;
    }


    @GetMapping(value="/generateCohortSchedule")
    public String generateCohortSchedule(){

    return "generateCohortSchedule";
    }


}
