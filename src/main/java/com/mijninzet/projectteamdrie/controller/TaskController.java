package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;


    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskRepository.getVacancies());
        return "showTasks";
    }



}


