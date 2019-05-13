package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Controller
public class TaskApplicationController {
    @Autowired
    TaskApplicationRepository taskApplicationRepo;

    @GetMapping(value = "/applicationBasket")
    public String applicationBasket() {
        return "applicationBasket";
    }

    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
    // hiermee kun je een sollicatatie in de mysql-tabel task_application opslaan
    @PostMapping(value="/showTasks")
    public String storeTaskApplication(@ModelAttribute Task task, Model model) {

        String role="Default_Docent";
        LocalDate todaysDate= LocalDate.now();

       model.addAttribute("taskId",task.getTaskId() );
        model.addAttribute("task.estimatedHours", task.getEstimatedHours());
//        model.addAttribute("naam1", taskApplication.getUserId());
//        model.addAttribute("naam1", taskApplication.getUserId());
//        model.addAttribute("naam1", taskApplication.getUserId());

        TaskApplication taskApplication= new TaskApplication(1,todaysDate,null,16,role,1000000);


        return "/showTasks";
    }


}
