package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class TaskApplicationController {

    @Autowired
    TaskApplicationRepository taskApplicationRepo;

    // deze methode wordt aangeropen bij een POST-request op url "showTasks"
    // hiermee kun je een sollicatatie in de mysql-tabel task_application opslaan
    @RequestMapping(method= RequestMethod.POST, value = "/showTasks")
    public void addTaskApplication(@RequestBody TaskApplication taskApplication) {
        taskApplicationRepo.save(taskApplication);
    }

    // hiermee kun je meerdere sollicataties (in een lijst)  in de mysql-tabel task_application opslaan
    @RequestMapping(method= RequestMethod.POST, value = "/showTasks")
    public void addTaskApplicationList(@RequestBody List<TaskApplication> taskAppList) {
        taskApplicationRepo.saveAll(taskAppList);
    }

}
