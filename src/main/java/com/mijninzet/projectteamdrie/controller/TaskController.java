package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskRepository.getVacancies());
        return "showTasks";
    }
}