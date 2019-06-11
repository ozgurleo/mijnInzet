package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.CurrentUser;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class WelcomeScreenControllerTeacher {

    @Autowired
    UserRepository userRepo;
    @Autowired
    private UserServiceImp userServiceImp;

//    @RequestMapping(value = "/helloTeacher")
//    public String hello() {
//        return "helloTeacher";
//    }

    @RequestMapping("/helloTeacher")
    public String welcomeTeacher(Model model, User user){
       User usercurrent = userRepo.findUserById(user.getCurrentUserId());
        model.addAttribute("user", usercurrent);
        return "dashboard/dashboard";
    }

}
