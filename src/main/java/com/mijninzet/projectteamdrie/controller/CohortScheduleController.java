package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.List;


@Controller
public class CohortScheduleController {

    @Autowired
    CohortScheduleRepository cohortScheduleRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SubjectRepository subjectRepo;

    public static final int DAYS_IN_WEEK=7;



    //hier worden het aantal weken in een cohort bepaalt
    public int getNumberOfCohortWeeks(LocalDate beginDate, LocalDate endDate){
        int cohortWeeks=0;
        //get nr off days in a cohort
        long days = ChronoUnit.DAYS.between(beginDate, endDate);

        // convert nr of days into whole weeks (ROUND UP)
        if(days%DAYS_IN_WEEK>0){
            cohortWeeks=(int) (days/DAYS_IN_WEEK) + 1;
        }else{
            cohortWeeks=(int) (days/DAYS_IN_WEEK);        }

        System.out.println(" getNumberOfCohortWeeks method is aangeroepen in de taskController");
        System.out.println("Aantal DAGEN : " + days + "---> zijn in totaal " + cohortWeeks + " cohortWEKEN");
        return cohortWeeks;
    }

    public String checkSubjectPreference(int teacherId,int subjectId){
        return "subjectNOK";
    }
    public String checkAvailability(int teacherId,String day,String dayPart){
        return "availabilityNOK";
    }
    public String checkTeacherHours(int teacherId,int subjectId){
        return "hoursNOK";
    }
    public String checkCohortOverlap(int teacherId,LocalDate datePlanned, String dayPart){
        return "overlapNOK";
    }


    @GetMapping(value="/generateCohortSchedule")
    public String generateCohortSchedule(Model model){
        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("preferences",subjectRepo.getPreferences());
        model.addAttribute("cohorts",cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());



        return "generateCohortSchedule";
    }


    @PostMapping(value="/generateCohortSchedule")
    public String insertSchedule(HttpServletRequest request, Model model){
        System.out.println("POST METHOD IS AANGEROEPEN:");
        System.out.println("-------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        System.out.println("daypart = :" + request.getParameter("daypart"));
        System.out.println("day = :" + request.getParameter("day"));
        System.out.println("date = : " + request.getParameter("date"));
        System.out.println("onderwerp = : " + request.getParameter("subject"));
        System.out.println("docent = : " + request.getParameter("user"));
        System.out.println("lokaal = : " + request.getParameter("classRoom"));
        System.out.println("check = : " + request.getParameter("check"));
        System.out.println("opslaan = : " + request.getParameter("opslaan"));
        System.out.println("-------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------");

        // Brahim code:  tbv checken welke parameterNamen met POST worden verstuurd:
//        Enumeration paramNames = request.getParameterNames();
//        while(paramNames.hasMoreElements()) {
//            String paramName = (String)paramNames.nextElement();
//            System.out.println("<tr><td>" + paramName + "</td>\n<td>");
//            String[] paramValues = request.getParameterValues(paramName);
//
//            // Read single valued data
//            if (paramValues.length == 1) {
//                String paramValue = paramValues[0];
//                if (paramValue.length() == 0)
//                    System.out.println("<i>No Value</i>");
//                else
//                    System.out.println(paramValue);
//            } else {
//                // Read multiple valued data
//                System.out.println("<ul>");
//
//                for(int i = 0; i < paramValues.length; i++) {
//                    System.out.println("<li>" + paramValues[i]);
//                }
//                System.out.println("</ul>");
//            }
//        }
//        System.out.println("</tr>\n</table>\n</body></html>");
        // End code


        model.addAttribute("checkSubject", checkSubjectPreference(1,1));
        model.addAttribute("checkAvailbility", checkAvailability(1,"monday","ochtend"));
        model.addAttribute("checkSubject", checkCohortOverlap(1,LocalDate date,"ochtend"));
        model.addAttribute("checkHours", checkTeacherHours(1,1));



        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("preferences",subjectRepo.getPreferences());
        model.addAttribute("cohorts",cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        System.out.println("DIT IS NA HET OPHALEN VD LASTCOHORTSCHEDULE!!!");
        return "generateCohortSchedule";
    }


}
