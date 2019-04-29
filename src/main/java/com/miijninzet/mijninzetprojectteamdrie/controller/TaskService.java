package com.miijninzet.mijninzetprojectteamdrie.controller;


import com.miijninzet.mijninzetprojectteamdrie.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskRepository.findAll());
        System.out.println(model);
        return "showTasks";
    }



}
