package com.mijninzet.projectteamdrie.controller;


import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskApplication {
    @Autowired
    private TaskApplicationRepository taskApplicationRepository;

    @RequestMapping(value = "/showTasks", method = RequestMethod.POST)
    public String storeApplication(Model model) {
        List<TaskApplication> taskApplications=new ArrayList<>();
        model.addAttribute("showTasks",taskApplicationRepository.saveAll(taskApplications) );
        return "showTasks";
    }

}
