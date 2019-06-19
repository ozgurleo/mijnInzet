package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.SubjectPreferenceRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
//@RequestMapping(value = "/teacherPreferences")
public class SubjectPreferencesController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectPreferenceRepository subjectPreferenceRepository;

//    @RequestMapping(value = "/showSubjects")
//    public String getSubjectList(Model model) {
//        List<Subject> subjectList = subjectRepository.findAll();
//        model.addAttribute("showSubjects", subjectList);
//        return "teacherSubjectPreferences";
//    }

    @RequestMapping(value = "/showSubjects")
    public String getSubjectList(Model model) {
        Map<Subject, Integer> subjectAndPreferenceMap = new LinkedHashMap<>();
        List<Subject> subjectList = subjectRepository.findAll();
        System.out.println("ongesorteerd");
        System.out.println(subjectList);
        Collections.sort(subjectList);
        System.out.println("gesorteerd");
        System.out.println(subjectList);
        int userId = UserSingleton.getInstance().getId();
        for (Subject subject : subjectList) {
            int subjectId = subject.getSubjectId();
            Integer preference = subjectPreferenceRepository.getPreferenceBySubjectIdAndUserId(subjectId, userId);
            preference = preference != null ? preference : 0;
            subjectAndPreferenceMap.put(subject, preference);
        }

        model.addAttribute("showSubjectsWithPreference", subjectAndPreferenceMap);
        return "teacherSubjectPreferences";
    }

//    private List<Map.Entry<Subject, Integer>> getEntryList() {
//        Map<Subject, Integer> subjectAndPreferenceMap = new HashMap<>();
//        List<Subject> subjectList = subjectRepository.findAll();
//        int userId = UserSingleton.getInstance().getId();
//        for (Subject s : subjectList) {
//            int subjectId = s.getSubjectId();
//            int preference = subjectPreferenceRepository.getPreferenceBySubjectIdAndUserId(subjectId, userId);
//            subjectAndPreferenceMap.put(s, preference);
//        }
//        return new ArrayList<>(subjectAndPreferenceMap.entrySet());
//    }

    @RequestMapping(value = "/submitPreferences", method = RequestMethod.POST)
    public String submitPreferences(HttpServletRequest request, Model model) {
        Enumeration paramNames = request.getParameterNames();
        System.out.println("enums" + paramNames);
//        preference(request);
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
            if (subjectPreferenceRepository.getPreferenceBySubjectIdAndUserId(subjectID, userId) == null)
                subjectPreferenceRepository.insertPreference(subjectPreference, subjectID, userId);
            else subjectPreferenceRepository.updatePreference(subjectPreference, subjectID, userId);
        }

        return getSubjectList(model);
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