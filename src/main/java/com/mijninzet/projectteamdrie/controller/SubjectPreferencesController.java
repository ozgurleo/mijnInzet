package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.SubjectPreference;
import com.mijninzet.projectteamdrie.repository.SubjectPreferenceRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/teacherPreferences")
public class SubjectPreferencesController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectPreferenceRepository subjectPreferenceRepository;

    @RequestMapping(value = "/showSubjects")
    public String getSubjectList(Model model) {
        List<Subject> subjectList = subjectRepository.findAll();
        model.addAttribute("showSubjects", subjectList);
        return "teacherSubjectPreferences";
    }

    @RequestMapping(value = "/submitSubjects", method = RequestMethod.POST)
    public String submitPreferences(@ModelAttribute("subjpref") SubjectPreference subjectPreference, Model model) {
//        SubjectPreference subjectPreference1 = new SubjectPreference();
        model.addAttribute("userID", subjectPreference.getUserId());
        model.addAttribute("Preference", subjectPreference.getPreference());
        model.addAttribute("Subject", subjectPreference.getSubject());

        subjectPreferenceRepository.save(subjectPreference);
        return "teacherSubjectPreferences";
    }
}