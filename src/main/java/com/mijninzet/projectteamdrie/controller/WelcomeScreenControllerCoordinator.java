package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeScreenControllerCoordinator {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;


    @RequestMapping("/helloCoordinator")
    public String welcomeTeacher(Model model, User user){
       User usercurrent = userRepo.findUserById(user.getCurrentUserId());

        if(roleRepo.findRoleNameByUserId(user.getCurrentUserId()).length>0){
        model.addAttribute("roles",roleRepo.findRoleNameByUserId(user.getCurrentUserId()));}
        model.addAttribute("user", usercurrent);
        return "helloCoordinator";
    }

}
