package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping(value = "/applicationBasket")
    public void addTaskApplication(@RequestBody TaskApplication taskAppList) {
        taskApplicationRepo.save(taskAppList);
    }

    @PostMapping(value = "/showTasks/{userId}/{applicationDate}/{unsubcribeDate}/{availableHours}/{role,taskID}")
    public void addTaskApplication(@RequestBody TaskApplication taskAppList) {
        LocalDate applicationDate= LocalDate.now();
                taskApplicationRepo.insertTaskapplication(userId, applicationDate, null, availableHours, role,taskID);
    }




    //    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
//    // hiermee kun je meerdere sollicataties (in een lijst)  in de mysql-tabel task_application opslaan
//    @PostMapping(value = "/applicationBasket")
//    public void addTaskApplicationList(@RequestBody List<TaskApplication> taskAppList) {
//        taskApplicationRepo.saveAll(taskAppList);
//    }
//
//
//    // deze methode komt van Huub"
//    // deze methode wordt aangeroepen bij een POST-request op url "showTasks"
//    // hiermee kun je meerdere sollicataties (in een lijst)  in de mysql-tabel task_application opslaan
//    @PostMapping(value = "/applicationBasket")
//    public String addTaskApplicationList(@ModelAttribute TaskApplication taskAppList, Model model) {
//        taskApplicationRepo.save(taskAppList);
//        //na de save ga dan terug naar webpage /showTasks
//        return "applicationBasket";
//    }

}
