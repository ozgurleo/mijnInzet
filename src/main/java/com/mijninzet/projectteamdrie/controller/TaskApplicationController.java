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


    @RequestMapping(value = "/applicationBasket")
    public String applBasket(){
        return "applicationBasket";
    }



    @PostMapping(value = "/taskApplications/{availableHours}/{taskID}")
    public String insertTaskAppl(@PathVariable("availableHours") int hours,@PathVariable("userId") int userId,@PathVariable("taskId") int taskID){

        LocalDate todaysDate=LocalDate.now();
        taskApplicationRepo.insertTaskapplication(userId, todaysDate, null, hours, "Docent",taskID);


        return "/showtasks/";
    }








}
