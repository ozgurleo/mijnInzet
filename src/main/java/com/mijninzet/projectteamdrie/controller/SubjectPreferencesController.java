package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
//@RequestMapping(value = "/teacherPreferences")
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


    @RequestMapping(value = "/submitPreferences", method = RequestMethod.PUT)
    public String submitPreferences(HttpServletRequest request, Model model) {
        Enumeration paramNames = request.getParameterNames();
        System.out.println("enums" + paramNames);
//        test(request);
        while (paramNames.hasMoreElements()) {
            String subjectName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(subjectName);
            System.out.println("-- Dit is de opgehaalde subjectname: " + subjectName);
            System.out.println("-- De opgehaalde subjectpreference: " + paramValues[0]);
            int subjectPreference;
            subjectPreference = Integer.parseInt(paramValues[0]);
            int subjectID = subjectRepository.getSubjectIdByName(subjectName);
            int userId = UserSingleton.getInstance().getId();
            System.out.printf("subjectpref: %d, subject id %d, user id: %d\n", subjectPreference, subjectID, userId);
            subjectPreferenceRepository.insertPreference(subjectPreference, subjectID, userId);
        }
        return "teacherSubjectPreferences";
    }

//    @RequestMapping(value = "/submitPreferences", method = RequestMethod.POST)
//    public String submitPreferences (@ModelAttribute ArrayList<SubjectPreference> subjectPreferences, Model model) {
//        System.out.println("De inhoud van subjectPreferences: ");
//
//        for (SubjectPreference s : subjectPreferences){
//            System.out.println(s.getId());
//            System.out.println(s.getPreference());
//            System.out.println(s.getSubject());
////        System.out.println(subjectPreference.getUser().getId());
//        }
////        subjectPreferenceRepository.save(subjectPreference);
////        System.out.println("save all is aangeroepen...");
//////        SubjectPreference subjectPreference1 = new SubjectPreference();
////        model.addAttribute("userID", subjectPreference.getUser().getId());
////        model.addAttribute("Preference", subjectPreference.getPreference());
////        model.addAttribute("Subject", subjectPreference.getSubject());
//        return "teacherSubjectPreferences";
//    }


}