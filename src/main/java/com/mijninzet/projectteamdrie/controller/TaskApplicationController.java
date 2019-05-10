package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class TaskApplicationController {

    @Autowired
    TaskApplicationRepository taskApplicationRepo;

    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
    // hiermee kun je een sollicatatie in de mysql-tabel task_application opslaan
    @PostMapping(value = "/showTasks")
    public void addTaskApplication(@RequestBody TaskApplication taskApplication) {
        taskApplicationRepo.save(taskApplication);
    }

    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
    // hiermee kun je meerdere sollicataties (in een lijst)  in de mysql-tabel task_application opslaan
    @PostMapping(value = "/showTasks")
    public void addTaskApplicationList(@RequestBody List<TaskApplication> taskAppList) {
        taskApplicationRepo.saveAll(taskAppList);
    }



    // deze methode komt van Huub"
    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
    // hiermee kun je meerdere sollicataties (in een lijst)  in de mysql-tabel task_application opslaan
    @PostMapping(value = "/showTasks")
    public String addTaskApplicationList(@ModelAttribute TaskApplication taskAppList, Model model) {
        taskApplicationRepo.save(taskAppList);
        //na de save ga dan terug naar webpage /showTasks
        return "showTasks";
    }

}
