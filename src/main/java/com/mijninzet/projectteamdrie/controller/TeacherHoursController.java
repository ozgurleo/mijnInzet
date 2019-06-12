package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.TeacherHoursRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.UserService;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherHoursController {



    @Autowired
    TeacherHoursRepository teacherHoursRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    CohortScheduleRepository cohortScheduleRepository;
    @Autowired
    CohortScheduleController cohortScheduleController;
    @Autowired
    CohortRepository cohortRepository;

    //Bereken beschikbare uren adhv FTE user
    public double calculateAvailableHoursTeacher(int userId) {
        User currentUser = userRepository.findUserById(userId);
        Double currentUserHoursTotal = userServiceImp.calculateTotalAvailableHours(userId);
        return currentUserHoursTotal;
    }

    //Update beschikbare uren docent
    public double updateAvailableHoursTeacher(int userId, double hoursUsed) {
        TeacherHours teacherHours = new TeacherHours();
        Double teachingHoursLeft = teacherHours.getTeachingHoursLeft();
        teachingHoursLeft = teachingHoursLeft - hoursUsed;

        return teachingHoursLeft;
    }

    @GetMapping("/teacherFTE")
    public String getTeacherFte(User user, Model model) {

        User currentUser = userService.findById(user.getCurrentUserId());
        List<Cohort> cohorts = cohortRepository.findAll();
        double availableHours = userServiceImp.calculateTotalAvailableHours(currentUser.getId());
        double userFTE = user.getFte();
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepository.getCohortScheduleByUser_Id(currentUser.getId());

        model.addAttribute("voornaam", currentUser.getName());
        model.addAttribute("achternaam", currentUser.getLastName());
        model.addAttribute("fte", userFTE);
        model.addAttribute("beschikbareUren", availableHours);
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("cohortSchedule", cohortScheduleList);


        return "teacherFTE";

    }

    @PostMapping("/teacherfTE/{cohortId}")
    public String getTeacherScheduleBasedOnCohort(User user, Model model, @PathVariable("cohortId") int cohortId) {

        User currentUser = userService.findById(user.getCurrentUserId());
        List<Cohort> cohorts = cohortRepository.findAll();
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepository.getCohortScheduleByCohort_CohortIdAndUser_Id(cohortId, currentUser.getId());

        model.addAttribute("cohortSchedule", cohortScheduleList);
        model.addAttribute("cohorts", cohorts);

        return "teacherFTE";

    }
}
