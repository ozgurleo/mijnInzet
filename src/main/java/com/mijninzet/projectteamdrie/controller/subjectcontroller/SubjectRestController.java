package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import com.mijninzet.projectteamdrie.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vak")
public class SubjectRestController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CohortScheduleRepository cohortRepository;

    @RequestMapping("/lijst")
    public List<Subject> findAll(){
        List<Subject>subjects=subjectService.findAll();
        return subjects;
    }

    @PostMapping("/addSubject")
    public Subject addSubject(@RequestBody Subject subject){
        subjectService.addSubject(subject);
        return subject;
    }
    @DeleteMapping("/deleteSubject/{id}")
    public void deleteSubject(@PathVariable int id){
        subjectService.deleteSubjectById(id);
    }

    @RequestMapping("/subjectCohortKopelen/{cohortId}")
    public List<CohortSchedule> findAllByCohortId (@PathVariable int cohortId){
        List<CohortSchedule>cohortSchedules=new ArrayList<>();
        cohortSchedules=cohortRepository.getAllByCohort_CohortId(cohortId);
        return cohortSchedules;
    }


}
