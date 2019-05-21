package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.model.entity.user.User;
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
    private static User user = new User();
    private static int userId=user.getCurrentUserId();

    @Autowired
    TaskApplicationRepository taskApplicationRepo;

    @Autowired
    TaskRepository taskRepository;



    public void deleteApplication(int taskId, String fullName){
    }


    @PostMapping(value = "/taskApplications/{taskId}/{availableHours}")
   public String insertTaskAppl(HttpServletRequest request, ModelMap model){
        System.out.println("methode is aangeroepen!");
        //testwaarde for userId is 1; totdat userid uit html gelezen kan worden
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
        String updateAction=request.getParameter("updateAppl");
        String deleteAction=request.getParameter("removeAppl");

        System.out.println("DE UIT TE VEOREN TAAK is: " + updateAction );
        System.out.println("de ingelezen taskid waarde is: " + tempId );
        System.out.println("de ingelezen fullname waarde is: " + fullName );
        System.out.println("de ingelezen availableHours waarde is: " + tempHours );

        //Convert variable to int
        int taskId=Integer.parseInt(tempId);
        int available_hours=Integer.parseInt(tempHours);

        //take action based on which button was clicked
        if(updateAction!=null){

            System.out.println("De user_id is ---> " + userId);
            taskApplicationRepo.updateHours(available_hours,userId,taskId);
            System.out.println("De UPDATE Methode is aangeroepen");

            }else if(deleteAction!=null){
            taskApplicationRepo.deleteApplication(taskId,userId);
            System.out.println("De DELETE Methode is aangeroepen");

                }else{
                     System.out.println("ER GAAT IETS FOUT--> GEEN BUTTON IS GEKLIKT!!");
        }

        model.addAttribute("applicationBasket",taskApplicationRepo.getApplicationOverview(userId));
        return "applicationBasket";
    }


    @GetMapping(value = "/applicationBasket")
    public String fillApplicationBasket(Object object, Model model){

        System.out.println("GET method voor ophalen solliciaties is aangeroepen");
        //model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview());
        model.addAttribute("applicationBasket",taskApplicationRepo.getApplicationOverview(userId));
        System.out.println("INHOUD VH OBJECT: ---> "+taskApplicationRepo.getApplicationOverview(userId).toString());
        return"applicationBasket";
    }





}
