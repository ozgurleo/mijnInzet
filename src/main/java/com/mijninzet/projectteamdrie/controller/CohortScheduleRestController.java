package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("cohortSchedule")
public class CohortScheduleRestController {
    @Autowired
    CohortScheduleRepository cohortScheduleRepository;

    @PostMapping("/scheduleKoppeling")
    public void saveSubjectToCohortSchedule(@RequestBody CohortSchedule cohortSchedule){
        cohortScheduleRepository.save(cohortSchedule);
    }

}
