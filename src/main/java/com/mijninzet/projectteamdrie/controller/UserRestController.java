package com.mijninzet.projectteamdrie.controller;


import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(path="/userss", method= RequestMethod.GET)
    public List<User> getAllEmployees(){
        return userService.getAllUsers();
    }
    @RequestMapping(value = "/userss/{id}", method = RequestMethod.GET)
    public Optional<User> getEmployeeById(@PathVariable("id") int id){
        return userService.getUser(id);
    }

}
