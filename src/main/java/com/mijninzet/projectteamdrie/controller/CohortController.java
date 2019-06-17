package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.*;

import java.time.LocalDate;



@Controller
@RequestMapping(value="/cohort")


public class CohortController {

    @Autowired
    CohortRepository cohortRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CohortScheduleRepository cohortSchedlRepo;

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



        // maak nieuwe CohortSchedule (default rooster) voor de nieuwe Cohort
        System.out.println("De begindateum= " + cohort.getBeginDate() );
        System.out.println("De begindateum= " + cohort.getEndDate() );
        System.out.println("De cohort= " + cohort.getCohortId() );
        makeDefaultCohortSchedule(cohort.getBeginDate(),cohort.getEndDate(),cohort.getCohortId());
        return ("redirect:/cohort/createCohort");
    }

    //hier cohortSchedule (default rooster) aangemaakt voor de nieuwe Cohort
    public void makeDefaultCohortSchedule(LocalDate beginDate, LocalDate endDate, int cohortId){



        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        for (LocalDate date = beginDate; date.isBefore(endDate); date = date.plusDays(1)) {
            CohortSchedule CsMorning = new CohortSchedule();
            CohortSchedule CsNoon = new CohortSchedule();
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayOfWeekName = dayOfWeek.name();
            switch (dayOfWeekName){
                case "MONDAY":
                    CsMorning.setDay("maandag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("maandag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "TUESAY":
                    CsMorning.setDay("dinsdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("dinsdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "WEDNESDAY":
                    CsMorning.setDay("woensdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("woensdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "THURSDAY":
                    CsMorning.setDay("donderdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("donderdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "FRIDAY":
                    CsMorning.setDay("vrijdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("vrijdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
            }
        }

    }


    @GetMapping(value="/generateCohortSchedule")
    public String generateCohortSchedule(){

    return "generateCohortSchedule";
    }


}
