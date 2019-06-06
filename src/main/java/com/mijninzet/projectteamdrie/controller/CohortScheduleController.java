package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;

import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    public static final int DAYS_IN_WEEK = 7;


    public String checkSubjectPreference(int teacherId, int subjectId) {
        String output="NO OUTPUT VALUE";
        int preference= subjectRepo.getSingleTeacherSubjectPref(teacherId,subjectId);

        if(preference==1 ){
            output="OK";

        }else if(preference==2){
            output="partly OK";
        }else if(preference==3){
            output = "NOK";
        }
        return output;
    }

    public String checkAvailability(int teacherId, String day, String dayPart) {
        return "availabilityNOK";
    }

    public String checkTeacherHours(int teacherId) {

        return "hoursNOK";
    }

    public String checkCohortOverlap(int teacherId, LocalDate datePlanned, String dayPart) {
        return "overlapNOK";
    }


    //hier worden het aantal weken in een cohort bepaalt
    public int getNumberOfCohortWeeks(LocalDate beginDate, LocalDate endDate) {
        int cohortWeeks = 0;
        //get nr off days in a cohort
        long days = ChronoUnit.DAYS.between(beginDate, endDate);

        // convert nr of days into whole weeks (ROUND UP)
        if (days % DAYS_IN_WEEK > 0) {
            cohortWeeks = (int) (days / DAYS_IN_WEEK) + 1;
        } else {
            cohortWeeks = (int) (days / DAYS_IN_WEEK);
        }

        System.out.println(" getNumberOfCohortWeeks method is aangeroepen in de taskController");
        System.out.println("Aantal DAGEN : " + days + "---> zijn in totaal " + cohortWeeks + " cohortWEKEN");
        return cohortWeeks;
    }

    // FOR AJAX google: ajax post to spring mvc controller



    @GetMapping(value = "/generateCohortSchedule")
    public String generateCohortSchedule(Model model) {
        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("preferences", subjectRepo.getPreferences());
        model.addAttribute("cohorts", cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        return "generateCohortSchedule";
    }


    @PostMapping(value = "/generateCohortSchedule")
    public String insertSchedule(HttpServletRequest request, Model model) {
        System.out.println("POST METHOD IS AANGEROEPEN:");
        System.out.println("-------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        System.out.println("daypart = :" + request.getParameter("daypart"));
        System.out.println("day = :" + request.getParameter("day"));
        System.out.println("date = : " + request.getParameter("date"));
        System.out.println("onderwerp = : " + request.getParameter("subjectMenu"));
        System.out.println("docent = : " + request.getParameter("teacherMenu"));
        System.out.println("lokaal = : " + request.getParameter("roomMenu"));
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

        // take date and split it up based on "-" and store in array of String-->
        // then convert Strings to int and set the LocalDate using the ints.
        String[] arrOfDate = request.getParameter("date").split("-", 0);
        int year = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int day = Integer.parseInt(arrOfDate[2]);
        LocalDate date = LocalDate.of(year, month, day);
        String selectedSubject=request.getParameter("subjectMenu");
        String selectedTeacher=request.getParameter("teacherMenu");
        String selectedRoom=request.getParameter("roomMenu");

        // add selection of the checkboxes to the modelAttribute
        model.addAttribute("selectedSubject",selectedSubject);
        model.addAttribute("selectedTeacher",selectedTeacher);
        model.addAttribute("selectedRoom",selectedRoom);


        //add result of contraints check to the modelAttribute
        model.addAttribute("checkSubject", checkSubjectPreference(6, 2));
        model.addAttribute("checkAvailbility", checkAvailability(6, "monday", "ochtend"));
        model.addAttribute("checkOverlap", checkCohortOverlap(6, date, "ochtend"));
        model.addAttribute("checkHours", checkTeacherHours(6));

        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("rooms", subjectRepo.getRooms());
        model.addAttribute("preferences", subjectRepo.getPreferences());
        model.addAttribute("cohorts", cohortScheduleRepo.getCohorts());
        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        System.out.println("DIT IS NA HET OPHALEN VD LASTCOHORTSCHEDULE!!!");
        return "generateCohortSchedule";
    }


}
