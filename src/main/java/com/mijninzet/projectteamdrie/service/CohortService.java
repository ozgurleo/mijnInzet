package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CohortService {
    @Autowired
    private CohortRepository cohortRepository;

    public List<Cohort> findAll(){
        List<Cohort>cohorts=new ArrayList<>();
        cohorts.addAll(cohortRepository.findAll());
        return cohorts;
    }

    public long getWeeksInCohort(int cohortId) {

        Cohort cohort = cohortRepository.getByCohortId(cohortId);
        LocalDate begindate = cohort.getBeginDate();
        LocalDate enddate = cohort.getEndDate();

        Duration duration = Duration.between(begindate,enddate);
        long days = duration.toDays();
        long weeks = days/7;
        return weeks;
    }

    public List<Integer> getCohortIds(){
        return cohortRepository.getCohortIds();
    }

}
