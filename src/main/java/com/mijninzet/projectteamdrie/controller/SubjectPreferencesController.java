package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubjectPreferencesController {
    @Autowired
    private SubjectRepository subjectRepository;



    @RequestMapping(value = "/showSubjects")
    public String makeSubjectList(Model model) {
        model.addAttribute("showSubjects", subjectRepository.findAll());
        return "teacherSubjectPreferences";
    }
}


