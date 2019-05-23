package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubjectPreferencesController {
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value = "/showSubjects")
    public String makeSubjectList(Model model) {
        model.addAttribute("showSubjects", subjectRepository.findAll());
        return "teacherSubjectPreferences";
    }

    @PostMapping(value = "/submitPreferences")
    public String submitPreferences(HttpServletRequest request, ModelMap model) {
        request.getParameter("subjectId");
        request.getParameter("preference");

        System.out.println(  "dit is een test voor buttonsubmit " +request.getParameter("preference") + " -"  );
        model.addAttribute("teacherSubjectPreferences", subjectRepository.findAll());
        return "teacherSubjectPreferences";
    }
}