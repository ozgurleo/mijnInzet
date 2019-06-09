package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.repository.*;

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
    @Autowired
    TeacherHoursRepository teacherHoursRepository;
    @Autowired
    CohortRepository cohortRepo;
    @Autowired
    StaffAvailibilityRepository stafAvailRepo;

    @Autowired
    ExceptionRepository exceptionRepo;


    public String generalAvail(int teacherId, String day, String dayPart, int cohortId) {
        System.out.println("CHECK METHODE generalAvail is aangeroepen");
        String output = "default";
        String generalAvail="default";
        String dayDaypart = day + "_" + dayPart;

        switch (dayDaypart) {
            case "maandag_ochtend":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getMondayMorning(cohortId, teacherId);
                break;
            case "maandag_middag":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getMondayNoon(cohortId, teacherId);
                break;
            case "dinsdag_ochtend":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getTuesdayMorning(cohortId, teacherId);
                break;
            case "dinsdag_middag":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getTuesdayNoon(cohortId, teacherId);
                break;
            case "woensdag_ochtend":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getWednesdayMorning(cohortId, teacherId);
                break;
            case "woensdag_middag":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getWednesdayNoon(cohortId, teacherId);
                break;
            case "donderdag_ochtend":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getThursdayMorning(cohortId, teacherId);
                break;
            case "donderdag_middag":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getThursdayNoon(cohortId, teacherId);
                break;
            case "vrijdag_ochtend":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getFridayMorning(cohortId, teacherId);
                break;
            case "vrijdag_middag":
                System.out.println("te checken dagdeel is : " + dayDaypart);
                output = stafAvailRepo.getFridayNoon(cohortId, teacherId);
                break;
        }

        if (output.equals("NEE")){
            generalAvail = "NOK";
        } else if (output.equals("JA")) {
            generalAvail = "OK";
        } else if(output==null){
            System.out.println("avaialbility is niet opgegegeven");
        }

        else{
            generalAvail="er gaat iets fout";
        }

        return generalAvail;
    }

    public String cohortOverlap(int teacherId, LocalDate dayDate, int currentCohortId, String dayPart) {
        System.out.println("CHECK METHODE cohortOverlap is aangeroepen");
        String overlap = "NOK";
        int previousCohort = currentCohortId - 1;
        Integer retrievedCohort = cohortScheduleRepo.getCohortOverlap(teacherId, dayPart, dayDate, previousCohort);
        if (retrievedCohort == null) {
            // retrievedohort =null means no overlap that date/daypart, so "OK" to be planned
            overlap = "OK";
        }
        return overlap;
    }

    public String checkIncident(int teacherId,LocalDate dayDate) {
        System.out.println("CHECK METHODE checkIncident is aangeroepen");
String output;
       String incident= exceptionRepo.getIncident(teacherId,dayDate);
        if(incident.equals("JA")||incident==null){
            output="OK";
        }else if(incident.equals("NEE")){
            output="NOK";
        }else {
            output="er gaat iets fout";
        }
        return output;
    }


    public String checkAvail(int teacherId, String day, String dayPart, LocalDate dayDate, int cohortId, int subjectId) {
        System.out.println("METHODE checkAvail is aangeroepen");
        String output="NOK";
        // check general availability;
        String avail=generalAvail(teacherId, day, dayPart, cohortId);
        //check cohortOverlap;
        String overlap =cohortOverlap(teacherId, dayDate, cohortId, dayPart);
        //check exception;
        String incident=checkIncident(teacherId,dayDate);
        //check preference;
        String preference= checkSubjectPreference(teacherId,subjectId);
        // check remaining teacherHours
         String hours = checkTeacherHours(teacherId,subjectId);

        if(incident.equals("NOK")){
            System.out.println("INCIDENT = NOK");
            output="NotAvailable";
        }else if(incident.equals("OK")|| incident.equals("GEEN")){
            if(avail.equals("NOK")){
                System.out.println("AVAILABILITY = NOK");
                output="NotAvailable";
            }else if (overlap.equals("NOK")){
                System.out.println("OVERLAP = NOK");
                output="NotAvailable";
            }else if(hours.equals("NOK")){
                System.out.println("HOURS = NOK");
                output="NoHoursLeft";
            }else if (preference.equals("NOK")){
                System.out.println("PREFERENCE = NOK");
                output="NotPrefered";
            }else{
                output="OK";
            }
        }

        return output;
    }



    public String checkSubjectPreference(int teacherId, int subjectId) {
        String output = "NO OUTPUT VALUE";
        Integer preference = subjectRepo.getSingleTeacherSubjectPref(teacherId, subjectId);
        if(preference==null){
            output="preference niet opgegeven";
        } else if (preference == 1) {
            output = "NOK";
        } else if (preference == 2) {
            output = "OK";
        } else if (preference == 3) {
            output = "OK";
        }
        return output;
    }


    public String checkTeacherHours(int teacherId, int subjectId) {

        Subject subject = subjectRepo.getBySubjectId(subjectId);
        String subjectname = subject.getSubjectName();
        TeacherHours teacherHours = teacherHoursRepository.findByUserId(teacherId);
        System.out.println("======================= Uit checkTeacherHours komt subject : " + subjectname + " en teacherHours : " + teacherHours);
        int realTeacherHours = 4;

        if (teacherHours != null) {
            if (!doesTeacherHaveExperienceWithSubject(teacherId, subjectId)) {
                if (teacherHours.getTeachingHoursLeft() < 8)
                    return "NOK";
                else {
                    return "OK";
                }
            } else {
                int yearsOfExperience = howManyYearsExperienceDoesTeacherHave(teacherId, subjectId);
                System.out.println(" -------------------  Uit checkTeacherHours komt : YearsOfExperience " + yearsOfExperience + "----------------------");
                switch (yearsOfExperience) {
                    case 1:
                        realTeacherHours = 6;
                        System.out.println("----------------------  Uit checkTeacherHours komt : realteacherHours " + realTeacherHours + " ---------------- ");
                        break;
                    case 2:
                        realTeacherHours = 4;
                        System.out.println("----------------------  Uit checkTeacherHours komt : realteacherHours " + realTeacherHours + " ---------------- ");
                        break;
                }
                if (teacherHours.getTeachingHoursLeft() < realTeacherHours) {
                    return "NOK";
                }
            }
            return "OK";
        } else {
            return "docentNietBekend";
        }
    }


    public boolean doesTeacherHaveExperienceWithSubject(int teacherId, int subjectId) {

        boolean experience = false;

        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectId(teacherId, subjectId);
        System.out.println("UIT doesTeacherHaveExpWithSubject komt cohortscheduleList " + cohortScheduleList + "De size is " + cohortScheduleList.size());
        if (cohortScheduleList.size() > 0) {
            experience = true;
        } else {
            experience = false;
        }
        return experience;
    }

    public int howManyYearsExperienceDoesTeacherHave(int teacherId, int subjectId) {
        int numberOfYearsExperience;
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectId(teacherId, subjectId);
        if (cohortScheduleList.size() == 1) {
            numberOfYearsExperience = 1;
        } else if (cohortScheduleList.size() == 2) {
            numberOfYearsExperience = 2;
        } else {
            numberOfYearsExperience = 3;
        }
        return numberOfYearsExperience;
    }


    @PostMapping(value = "/generateCohortSchedule/check")
    public @ResponseBody
    String checkSchedule(HttpServletRequest request) {
        String result="default";
        System.out.println("!!!!!!!!! ajaxPOSTTest is aangeroepen !!!!!!");
        System.out.println("CohortScheduleID : " + request.getParameter("scheduleId"));
        System.out.println("Button clicked=:" + request.getParameter("button"));
        System.out.println("  cohortnr =" + request.getParameter("cohortnr"));
        System.out.println("  datum = " + request.getParameter("dateDay"));
        System.out.println("  dag = " + request.getParameter("day"));
        System.out.println("  dagdeel = " + request.getParameter("daypart"));
        System.out.println("  subjectId = " + request.getParameter("subjectnr"));
        System.out.println("  teacherId = " + request.getParameter("teachernr"));
        String buttonClicked = request.getParameter("button");
        System.out.println("button clicked check ---> it equals " + buttonClicked );
        int cohortId = Integer.parseInt(request.getParameter("cohortnr"));
        String[] arrOfDate = request.getParameter("dateDay").split("-", 0);
        int year = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int day = Integer.parseInt(arrOfDate[2]);
        LocalDate dayDate = LocalDate.of(year, month, day);
        String weekDay = request.getParameter("day");
        String dayPart = request.getParameter("daypart");
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectnr"));
        int teacherId = Integer.parseInt(request.getParameter("teachernr"));


        if(buttonClicked.equals("check")){
            System.out.println("CHECK METHODE IS AANGEROEPEN");
          result= checkAvail(teacherId,weekDay, dayPart,dayDate,cohortId,subjectId);

        } else if (buttonClicked.equals("save")) {
            cohortScheduleRepo.storeSchedule(teacherId, scheduleId);
        } else {
            result = "default";
        }
        System.out.println("Result dat teruggestuurd wordt via AJAX: " + result);
        return result;
    }


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

//
//    @PostMapping(value = "/generateCohortSchedule")
//    public String insertSchedule(HttpServletRequest request, Model model) {
//        System.out.println("POST METHOD IS AANGEROEPEN:");
//        System.out.println("-------------------------------------------------------------");
//        System.out.println("-------------------------------------------------------------");
//        System.out.println("daypart = :" + request.getParameter("daypart"));
//        System.out.println("day = :" + request.getParameter("day"));
//        System.out.println("date = : " + request.getParameter("date"));
//        System.out.println("onderwerp = : " + request.getParameter("subjectMenu"));
//        System.out.println("docent = : " + request.getParameter("teacherMenu"));
//        System.out.println("lokaal = : " + request.getParameter("roomMenu"));
//        System.out.println("check = : " + request.getParameter("check"));
//        System.out.println("opslaan = : " + request.getParameter("opslaan"));
//        System.out.println("-------------------------------------------------------------");
//        System.out.println("-------------------------------------------------------------");
//
//        // Brahim code:  tbv checken welke parameterNamen met POST worden verstuurd:
////        Enumeration paramNames = request.getParameterNames();
////        while(paramNames.hasMoreElements()) {
////            String paramName = (String)paramNames.nextElement();
////            System.out.println("<tr><td>" + paramName + "</td>\n<td>");
////            String[] paramValues = request.getParameterValues(paramName);
////
////            // Read single valued data
////            if (paramValues.length == 1) {
////                String paramValue = paramValues[0];
////                if (paramValue.length() == 0)
////                    System.out.println("<i>No Value</i>");
////                else
////                    System.out.println(paramValue);
////            } else {
////                // Read multiple valued data
////                System.out.println("<ul>");
////
////                for(int i = 0; i < paramValues.length; i++) {
////                    System.out.println("<li>" + paramValues[i]);
////                }
////                System.out.println("</ul>");
////            }
////        }
////        System.out.println("</tr>\n</table>\n</body></html>");
//        // End code
//
//        // take date and split it up based on "-" and store in array of String-->
//        // then convert Strings to int and set the LocalDate using the ints.
//        String[] arrOfDate = request.getParameter("date").split("-", 0);
//        int year = Integer.parseInt(arrOfDate[0]);
//        int month = Integer.parseInt(arrOfDate[1]);
//        int day = Integer.parseInt(arrOfDate[2]);
//        LocalDate date = LocalDate.of(year, month, day);
//        String selectedSubject = request.getParameter("subjectMenu");
//        String selectedTeacher = request.getParameter("teacherMenu");
//        String selectedRoom = request.getParameter("roomMenu");
//
//
//        // add selection of the checkboxes to the modelAttribute
//        model.addAttribute("selectedSubject", selectedSubject);
//        model.addAttribute("selectedTeacher", selectedTeacher);
//        model.addAttribute("selectedRoom", selectedRoom);
//
//
//        //add result of contraints check to the modelAttribute
//        model.addAttribute("checkSubject", checkSubjectPreference(6, 1));
//        model.addAttribute("checkAvailbility", checkAvailability(6, "monday", "ochtend",date));
//        model.addAttribute("checkOverlap", checkCohortOverlap(6, date, "ochtend"));
//        model.addAttribute("checkHours", checkTeacherHours(Integer.parseInt(request.getParameter("teacherMenu")), Integer.parseInt(request.getParameter("subjectMenu"))));
//
//        model.addAttribute("subjects", subjectRepo.getSubjects());
//        model.addAttribute("teachers", userRepo.getTeachers());
//        model.addAttribute("rooms", subjectRepo.getRooms());
//        model.addAttribute("preferences", subjectRepo.getPreferences());
//        model.addAttribute("cohorts", cohortScheduleRepo.getCohorts());
//        model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
//        System.out.println("DIT IS NA HET OPHALEN VD LASTCOHORTSCHEDULE!!!");
//        return "generateCohortSchedule";
//    }


}
