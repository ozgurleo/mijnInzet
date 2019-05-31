package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeScreenControllerAdmin {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/helloAdmin")
    public String welcomeAdmin(User user, Model model) {
        User currentUser = userRepository.findUserById(user.getCurrentUserId());
        model.addAttribute("user", currentUser);
        return "helloAdmin";
    }
}
