package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CohortService {
    @Autowired
    private CohortRepository cohortRepository;

    public List<Cohort> findAll(){
        List<Cohort>cohorts=new ArrayList<>();
        cohortRepository.findAll()
                .forEach(cohorts::add);
        return cohorts;
    }

}
