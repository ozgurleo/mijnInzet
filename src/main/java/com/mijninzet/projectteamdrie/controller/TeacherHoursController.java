package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.repository.TeacherHoursRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.UserService;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    //Bereken beschikbare uren adhv FTE user
    public double calculateAvailableHoursTeacher(int userId){
        User currentUser = userRepository.findUserById(userId);
        Double currentUserHoursTotal = userServiceImp.calculateTotalAvailableHours(userId);
        return currentUserHoursTotal;
    }

    //Update beschikbare uren docent
    public double updateAvailableHoursTeacher(int userId, double hoursUsed){
        TeacherHours teacherHours = new TeacherHours();
        Double teachingHoursLeft = teacherHours.getTeachingHoursLeft();
        teachingHoursLeft = teachingHoursLeft - hoursUsed;

        return teachingHoursLeft;
    }

//    @GetMapping
//    public Double getLeftHoursTeacher(User user, Model model){
//        TeacherHours teacherHours = new TeacherHours();
//        User currentUser = userService.findById(user.getCurrentUserId());
//        Double teachingHoursLeft = teacherHours.getTeachingHoursLeft();
//
//        return teachingHoursLeft;
//
//    }
}
