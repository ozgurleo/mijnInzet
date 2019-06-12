package com.mijninzet.projectteamdrie.controller;

//import com.mijninzet.projectteamdrie.model.entity.user.Role;
//import com.mijninzet.projectteamdrie.model.entity.user.User;
//import com.mijninzet.projectteamdrie.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//import java.util.Optional;

import com.mijninzet.projectteamdrie.model.entity.user.Role;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.service.UserService;
import com.mijninzet.projectteamdrie.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private RoleRepository roleRepository;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    //}

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User theUser) {
        userService.saveUser(theUser);
        return "redirect:/users/list";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String getAllRoles(Model model) {
        List<Role> rolelist = roleRepository.findAll();
        model.addAttribute("roles", rolelist);
        return ("register");
    }

    @RequestMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User theUser = new User();
        List<Role> rolelist = roleRepository.findAll();
        model.addAttribute("roles", rolelist);
        model.addAttribute("user", theUser);
        // return "user-form";
        return "register";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model model) {
        User theUser = userService.findById(theId);
        model.addAttribute("user", theUser);
        return "user-form";

    }

    //
//
//    @RequestMapping("/users/{id}")
//    public Optional<User> getUser(@PathVariable int id){
//       return userService.getUser(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST,value = "/users")
//    public void addUser(User user){
//
//        userService.addUser(user);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT,value = "/users/{id}")
//    public void updateUser(int id,User user){
//        userService.updateUser(id,user);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE,value = "/users/{id}")
//    public void deleteUser(int id){
//
//        userService.deleteUser(id);
//    }
    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId, Model model) {
        userService.deleteUserById(theId);

        return "redirect:/users/list";

    }
//    @RequestMapping("/login")
//    public String getLoginForm(){
//
//        return "login";
//    }
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login( Model model,
//                         @ModelAttribute("user") User user ) {
//
//
//        List<User> users = userService.getAllUsers();
//
//        for (int i = 0; i < users.size(); i++) {
//
//            String username = users.get(i).getUsername();
//            String password = users.get(i).getPassword();
//
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                return "hello";
//            }
//            model.addAttribute("invalidCredentials", true);
//        }
//        return "login";
//    }
//}
}