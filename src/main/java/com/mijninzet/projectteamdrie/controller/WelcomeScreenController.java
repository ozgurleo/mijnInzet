package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeScreenController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private UserServiceImp userServiceImp;



    @RequestMapping("/welcomeScreen")
    public String welcome(Model model, User user){
       User usercurrent = userRepo.findUserById(User.getCurrentUserId());

        if(roleRepo.findRoleNameByUserId(User.getCurrentUserId()).length>0){
        model.addAttribute("roles",roleRepo.findRoleNameByUserId(User.getCurrentUserId()));}
        model.addAttribute("user", usercurrent);
        return "welcomeScreen";
    }



    @RequestMapping(value = "/helloAdmin")
    public String welcomeAdmin(User user, Model model) {
        User currentUser = userRepo.findUserById(User.getCurrentUserId());
        model.addAttribute("user", currentUser);
        return "helloAdmin";
    }

    @RequestMapping("/helloScheduler")
    public String welcomeScheduler(Model model, User user){
        User usercurrent = userRepo.findUserById(User.getCurrentUserId());
        model.addAttribute("user", usercurrent);
        return "helloScheduler";
    }

    @RequestMapping("/helloTeacher")
    public String welcomeTeacher(Model model, User user){
        User usercurrent = userRepo.findUserById(User.getCurrentUserId());
        model.addAttribute("user", usercurrent);
        return "helloTeacher";
    }

    @RequestMapping("/helloCoordinator")
    public String welcomeCoordinator(Model model, User user){
        User usercurrent = userRepo.findUserById(User.getCurrentUserId());
        model.addAttribute("user", usercurrent);
        return "helloCoordinator";
    }

}
