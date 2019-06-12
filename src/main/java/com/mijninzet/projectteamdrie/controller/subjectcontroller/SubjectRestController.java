package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vak")
public class SubjectRestController {
    @Autowired
    private SubjectService subjectService;

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


}
