package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
//    private SessionFactory sessionFactory;
//    private AdministratorDAO administratorDAO = new AdministratorDAO(sessionFactory);

    @Autowired
private UserService userService;

    @RequestMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @RequestMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable int id){
       return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/users")
    public void addUser(User user){
     userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/users/{id}")
    public void updateUser(int id,User user){
        userService.updateUser(id,user);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/users/{id}")
    public void deleteUser(int id){
    userService.deleteUser(id);
    }

    @RequestMapping("/login")
    public String getLoginForm(){
        return "login";
    }
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login( Model model) {
//        List<User> users=userService.getAllUsers();
//        model.addAttribute("users",users);
//        for(int i=0; i<users.size();i++){
//            String username=users.get(i).getUsername();
//            String password=users.get(i).getPassword();
//
//            if("admin".equals(username)&&"admin".equals(password)){
//                return "hello";
//            }
//            model.addAttribute("invalidCredentials",true);
//            return "login";
//
//    }
//        return
//}
}
