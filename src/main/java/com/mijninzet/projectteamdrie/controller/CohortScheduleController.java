package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.repository.*;

import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    CohortRepository cohortRepository;
    @Autowired
    StaffAvailibilityRepository staffAvailrepo;

    @Autowired
    ExceptionRepository exceptionRepo;


    private String totalCheck(int cohortId, int teacherId, LocalDate dayDate, String dayPart, String weekDay, int subjectId) {
        String result = "default";
        String hours;
        String avail = checkGeneralAvail(teacherId, weekDay, dayPart, cohortId);
        String overlap = checkCohortOverlap(cohortId, teacherId, dayDate, dayPart);
        String incident = checkTeacherException(teacherId, dayDate);
        String dayAvail;
        String subjectPref = checkSubjectPreference(teacherId, subjectId);
        if (subjectPref.equals("NON")) {
            subjectPref = "OK";
        }
        System.out.println("RESULT OF PREFERENCE = " + subjectPref);

        String checkHours = checkTeacherHours(teacherId, subjectId,cohortId);
        if (checkHours.equals("OK") || checkHours.equals("NON")) {
            hours = "OK";
        } else {
            hours = "NOK";
        }
        System.out.println("RESULT OF TEACHER HOURS = " + hours);

        //check if availability is OK or NOK
        if ((avail.equals("NOK") && incident.equals("OK") && overlap.equals("OK")) ||
                (avail.equals("OK") && ((incident.equals("OK") ||
                        incident.equals("NON")) && overlap.equals("OK")))) {
            dayAvail = "OK";
        } else {
            dayAvail = "NOK";
        }

        System.out.println("RESULT OF THE dayAvail = " + dayAvail);

        String total = hours + "-" + dayAvail + "-" + subjectPref;

        if (total.equals("OK-OK-OK")) {
            result = "OK";
        } else if (total.equals("NOK-NOK-NOK")) {
            result = "NOK";
        } else if (total.equals("NOK-OK-OK")) {
            result = "hourNOK_restOK";
        } else if (total.equals("OK-NOK-OK")) {
            result = "availNOK_restOK";
        } else if (total.equals("OK-OK-NOK")) {
            result = "subjectNOK_restOK";
        } else if (total.equals("NOK-NOK-OK")) {
            result = "hoursNOK_availNOK_OK";
        } else if (total.equals("OK-NOK-NOK")) {
            result = "OK_availNOK_prefNOK";
        } else if (total.equals("NOK-OK-NOK")) {
            result = "hoursNOK_OK_prefNOK";
        }


        System.out.println("RESULT OF TOTAL CHECK = " + result);
        return result;
    }

    public String checkGeneralAvail(int teacherId, String day, String dayPart, int cohortId) {
        String dayDayPart = day + "_" + dayPart;
        String result = "default";
        switch (dayDayPart) {
            case "maandag_ochtend":
                result = staffAvailrepo.getMondayMorning(cohortId, teacherId);
                break;
            case "dinsdag_ochtend":
                result = staffAvailrepo.getTuesdayMorning(cohortId, teacherId);
                break;
            case "woensdag_ochtend":
                result = staffAvailrepo.getWednesdayMorning(cohortId, teacherId);
                break;
            case "donderdag_ochtend":
                result = staffAvailrepo.getThursdayMorning(cohortId, teacherId);
                break;
            case "vrijdag_ochtend":
                result = staffAvailrepo.getFridayMorning(cohortId, teacherId);
                break;

            case "maandag_middag":
                result = staffAvailrepo.getMondayNoon(cohortId, teacherId);
                break;
            case "dinsdag_middag":
                result = staffAvailrepo.getTuesdayNoon(cohortId, teacherId);
                break;
            case "woensdag_middag":
                result = staffAvailrepo.getWednesdayNoon(cohortId, teacherId);
                break;
            case "donderdag_middag":
                result = staffAvailrepo.getThursdayNoon(cohortId, teacherId);
                break;
            case "vrijdag_middag":
                result = staffAvailrepo.getFridayNoon(cohortId, teacherId);
                break;
        }

        if (result.equals("JA")) {
            result = "OK";
        } else if (result.equals("NEE")) {
            result = "NOK";
        } else {
            result = "NON";
        }

        System.out.println("RESULT OF THE GENERAL AVAILABILITY = " + result);

        return result;
    }

    public String checkCohortOverlap(int cohortId, int teacherId, LocalDate datePlanned, String dayPart) {
        String result = "default";
        int scheduleId = 0;
        String tempId = cohortScheduleRepo.getDateDaypartOverlap(cohortId, datePlanned, dayPart, teacherId);
        if (tempId == null) {
            scheduleId = 0;
        } else {
            scheduleId = Integer.parseInt(tempId);
        }

        if (scheduleId > 0) {
            result = "NOK";
        } else {
            result = "OK";
        }
        System.out.println("RESULT OF THE COHORT OVERLAP = " + result);
        return result;
    }

    public String checkSubjectPreference(int teacherId, int subjectId) {
        String result = "default";
        String preference = subjectRepo.getSingleTeacherSubjectPref(teacherId, subjectId);
        if (preference == null) {
            result = "NON";
        } else if (preference.equals("1")) {
            result = "OK";

        } else if (preference.equals("2")) {
            result = "OK";
        } else if (preference.equals("3")) {
            result = "NOK";
        }

        System.out.println("RESULT OF THE SUBJECT PREFERENCE = " + result);
        return result;
    }


    public String checkTeacherException(int teacherId, LocalDate datePlanned) {
        String result = "default";

        String incidentText = exceptionRepo.getIncident(teacherId, datePlanned);

        if (incidentText == null) {
            result = "NON";
        } else if (incidentText.equals("JA")) {
            result = "OK";
        } else if (incidentText.equals("NEE")) {
            result = "NOK";
        }
        System.out.println("RESULT OF THE TEACHER INCIDENT = " + result);
        return result;
    }

    public String checkTeacherHours(int teacherId, int subjectId, int cohortId) {

        Subject subject = subjectRepo.getBySubjectId(subjectId);
        String subjectname = subject.getSubjectName();
        TeacherHours teacherHours = teacherHoursRepository.findByUserId(teacherId);
        int realTeacherHours = 4;

        if (teacherHours != null) {
            if (!doesTeacherHaveExperienceWithSubject(teacherId, subjectId,cohortId)) {
                if (teacherHours.getTeachingHoursLeft() < 8)
                    return "NOK";
                else {
                    return "OK";
                }
            } else {
                int yearsOfExperience = howManyYearsExperienceDoesTeacherHave(teacherId, subjectId,cohortId);
                switch (yearsOfExperience) {
                    case 1:
                        realTeacherHours = 6;
                        break;
                    case 2:
                        realTeacherHours = 4;
                        break;
                }
                if (teacherHours.getTeachingHoursLeft() < realTeacherHours) {
                    return "NOK";
                }
            }
            return "OK";
        } else {
            return "NON";
        }
    }


    public boolean doesTeacherHaveExperienceWithSubject(int teacherId, int subjectId,int cohortId) {

        boolean experience = false;

        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectIdAndAndCohortNot(teacherId, subjectId,cohortId);
        if (cohortScheduleList.size() > 0) {
            experience = true;
        } else {
            experience = false;
        }
        return experience;
    }

    public int howManyYearsExperienceDoesTeacherHave(int teacherId, int subjectId, int cohortId) {
        int numberOfYearsExperience;
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectIdAndAndCohortNot(teacherId, subjectId,cohortId);
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

        System.out.println("!!!!!!!!! ajaxPOSTTest is aangeroepen !!!!!!");
        System.out.println("button clicked =" + request.getParameter("button"));
        System.out.println("cohortnr =" + request.getParameter("cohortnr"));
        System.out.println("datum = " + request.getParameter("dateDay"));
        System.out.println("dag = " + request.getParameter("day"));
        System.out.println("dagdeel = " + request.getParameter("daypart"));
        System.out.println("subjectId = " + request.getParameter("subjectnr"));
        System.out.println("teacherId = " + request.getParameter("teachernr"));

        String buttonClicked = request.getParameter("button");
        int cohortId = Integer.parseInt(request.getParameter("cohortnr"));
        String[] arrOfDate = request.getParameter("dateDay").split("-", 0);
        int year = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int day = Integer.parseInt(arrOfDate[2]);
        LocalDate dayDate = LocalDate.of(year, month, day);

        String weekDay = request.getParameter("day");
        String dayPart = request.getParameter("daypart");
        int subjectId = Integer.parseInt(request.getParameter("subjectnr"));
        int teacherId = Integer.parseInt(request.getParameter("teachernr"));

        String result = "";
        if (buttonClicked.equals("check")) {
            result = totalCheck(cohortId, teacherId, dayDate, dayPart, weekDay, subjectId);
            System.out.println("RESULT AFTER CHECK = " + result);

            return result;
        }
        if (buttonClicked.equals("save")) {
            CohortSchedule newCS = new CohortSchedule();
            newCS.setClassRoom("");
            newCS.setDay(weekDay);
            newCS.setDaypart(dayPart);
            newCS.setDate(dayDate);
            newCS.setCohort(cohortRepository.getByCohortId(cohortId));
            newCS.setSubject(subjectRepo.getBySubjectId(subjectId));
            newCS.setUser(userRepo.findUserById(teacherId));
            cohortScheduleRepo.save(newCS);

            result = totalCheck(cohortId, teacherId, dayDate, dayPart, weekDay, subjectId);
            return result;
        }

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

        return result;

    }


    public List<CohortSchedule> getAllSchedulesInCohort(int cohortId) {

        List<CohortSchedule> listCohortSchedulesByCohortid = cohortScheduleRepo.getAllByCohort_CohortId(cohortId);

        Cohort cohort = cohortRepository.getByCohortId(cohortId);

        LocalDate beginDate = cohort.getBeginDate();
        LocalDate endDate = cohort.getEndDate();

        return listCohortSchedulesByCohortid;

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



}
