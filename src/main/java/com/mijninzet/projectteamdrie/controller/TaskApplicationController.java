package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import com.mijninzet.projectteamdrie.repository.TaskRepository;
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

    @Autowired
    TaskRepository taskRepository;

//    @RequestMapping(value = "/applicationBasket")
//    public String makeVacancyList(Model model) {
//        model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview());
//        return "applicationBasket";
//    }

    public void saveUpdateHours(int taskId, int hours, String fullName){

    }
    public void deleteApplication(int taskId, String fullName){

    }

    @PostMapping(value = "/taskApplications/{taskId}/{availableHours}")
   public String insertTaskAppl(HttpServletRequest request, ModelMap model){
        System.out.println("methode is aangeroepen!");
        //testwaarde for userId is 1; totdat userid uit html gelezen kan worden
        int userId=1;
        LocalDate todaysDate=LocalDate.now();

        //get the data from httpservletRequest and put in variable
        String tempId=request.getParameter("taskId");
        String tempHours=request.getParameter("availableHours");

        //Convert variable to int
        int iD=Integer.parseInt(tempId);
        int hours=Integer.parseInt(tempHours);

        System.out.println("de ingelezen taskid waarde is: " + iD );
        System.out.println("de ingelezen availableHours waarde is: " + hours );

        // insert the data into database
         taskApplicationRepo.insertTaskapplication(userId, todaysDate, null, hours, "Docent",iD);

         // get latests data from db en send to showtasks.html
        model.addAttribute("showTasks", taskRepository.getVacancies());
        return "showtasks";
    }

    @PostMapping(value = "/taskApplications/{taskId}/{fullName}/{availHours}")
    public String updateTaskApplications(HttpServletRequest request, ModelMap model){
        //get the data from httpservletRequest and put in variable
        String tempId=request.getParameter("taskId");
        String fullName=request.getParameter("fullName");
        String tempHours=request.getParameter("availHours");
        String actionToTake=request.getParameter("updateAppl");

        System.out.println("DE UIT TE VEOREN TAAK is: " + actionToTake );
        System.out.println("de ingelezen taskid waarde is: " + tempId );
        System.out.println("de ingelezen fullname waarde is: " + fullName );
        System.out.println("de ingelezen availableHours waarde is: " + tempHours );


        //Convert variable to int
        int taskId=Integer.parseInt(tempId);
        int availHours=Integer.parseInt(tempHours);

        //take action based on which button was clicked
        if(actionToTake=="updateAppl"){
            saveUpdateHours(taskId,availHours,fullName);
        }else if(actionToTake=="removeAppl"){
            deleteApplication(taskId, fullName);
        }else{
            System.out.println("ER GAAT IETS FOUT--> GEEN BUTTON IS GEKLIKT!!");
        }

        model.addAttribute("applicationBasket",taskApplicationRepo.getApplicationOverview());
        return "applicationBasket";
    }


    @GetMapping(value = "/applicationBasket")
    public String fillApplicationBasket(Object object, Model model){
        //model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview());
        model.addAttribute("applicationBasket",taskApplicationRepo.getApplicationOverview());
        System.out.println("INHOUD VH OBJECT: ---> "+taskApplicationRepo.getApplicationOverview().toString());
        return"applicationBasket";
    }





}
