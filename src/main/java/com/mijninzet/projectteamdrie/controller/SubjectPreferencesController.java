package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.SubjectPreference;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/teacherPreferences")
public class SubjectPreferencesController {
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value = "/showSubjects")
    public String getSubjectList(Model model) {
        List<Subject> subjectList = subjectRepository.findAll();
        model.addAttribute("showSubjects", subjectList);
        return "teacherSubjectPreferences";
    }

    @RequestMapping(value = "/submitSubjects", method = RequestMethod.POST)
    public String submitPreferences(SubjectPreference subjectPreference, Model model) {
//        model.addAttribute("userID", subjectPreference.getUserId());
//        model.addAttribute("Subject", subjectPreference.getSubject());
//        model.addAttribute("Preference", subjectPreference.getPreference());
//
//        subjectRepository.save(subjectPreference);
        return "teacherSubjectPreferences";
    }
}