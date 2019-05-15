package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Controller
public class TaskApplicationController {
    @Autowired
    TaskApplicationRepository taskApplicationRepo;



    @PostMapping(value = "/taskApplications/{taskId}/{availableHours}")
   public String insertTaskAppl(HttpServletRequest request, ModelMap model){
        System.out.println("methode is aangeroepen!");
        //testwaarde for userId is 1; totdat userid uit html gelezen kan worden
        int userId=1;
        LocalDate todaysDate=LocalDate.now();

        //get the data from httpservletRequest and put in variable
        String tempId=request.getParameter("taskId");
        String tempHours=request.getParameter("availableHours");

        //Convert to Strings
        int iD=Integer.parseInt(tempId);
        int hours=Integer.parseInt(tempHours);

        System.out.println("de ingelezen taskid waarde is: " + iD );
        System.out.println("de ingelezen availableHours waarde is: " + hours );

        taskApplicationRepo.save(new TaskApplication(userId, todaysDate, null, hours, "Docent",iD));

      //  taskApplicationRepo.insertTaskapplication(userId, todaysDate, null, hours, "Docent",iD);


        return "redirect:/showtasks";
    }

//    @PostMapping(value = "/taskApplications/{taskId}/{availableHours}")
//    @ResponseBody
//    public String insertTaskAppl(@PathVariable String taskId, @PathVariable  String availableHours){
//        System.out.println("methode is aangeroepen!");
//
//        int iD=Integer.parseInt(taskId);
//        int hours=Integer.parseInt(availableHours);
//
//        System.out.println("de ingelezen taskid waarde is: " + taskId );
//        System.out.println("de ingelezen availableHours waarde is: " + availableHours );
//
//
//
//        //testwaarde for userId is 1; totdat userid uit html gelezen kan worden
//        int userId=1;
//        LocalDate todaysDate=LocalDate.now();
//        taskApplicationRepo.insertTaskapplication(userId, todaysDate, null, hours, "Docent",iD);
//
//
//        return "redirect:/showtasks";
//    }






}
