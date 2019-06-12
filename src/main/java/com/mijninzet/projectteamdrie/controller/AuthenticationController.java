package com.mijninzet.projectteamdrie.controller;

import javax.validation.Valid;

import com.mijninzet.projectteamdrie.model.entity.user.Role;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.service.UserService;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AuthenticationController {

    UserServiceImp userServiceImp;
    @Autowired
    UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> rolelist = roleRepository.findAll();
        modelAndView.addObject("roles", rolelist);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register"); // resources/template/register.html
        System.out.println("! ViewName from ModelandView from registermethod: " + modelAndView.getViewName());
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        userToSave = user;
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "user already exists!");
        }
        // we will save the user if, no binding errors
        else {
            userService.saveUser(user);
//            userServiceImp.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        List<Role> rolelist = roleRepository.findAll();
        modelAndView.addObject("roles", rolelist);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("helloTeacher");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register"); // resources/template/admin.html
        return modelAndView;
    }

    private User userToSave;

    public User getUserToSave() {
        return userToSave;
    }
}










