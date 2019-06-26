package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import com.mijninzet.projectteamdrie.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class TaskApplicationController {
    private final TaskApplicationRepository taskApplicationRepo;
    private final TaskRepository taskRepository;

    public TaskApplicationController(TaskApplicationRepository taskApplicationRepo, TaskRepository taskRepository) {
        this.taskApplicationRepo = taskApplicationRepo;
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/taskApplications/{taskId}/{availableHours}")
    public String insertTaskAppl(HttpServletRequest request, ModelMap model) {
        System.out.println("methode is aangeroepen!");
        //testwaarde for userId is 1; totdat userid uit html gelezen kan worden
        LocalDate todaysDate = LocalDate.now();
        //get the data from httpservletRequest and put in variable
        String tempId = request.getParameter("taskId");
        String tempHours = request.getParameter("availableHours");
        if (tempHours.matches("^[0-9]+$")) {
            //Convert variable to int
            int taskId = Integer.parseInt(tempId);
            int hours = Integer.parseInt(tempHours);
            //haal de userId op vd loggedin user uit de Singleton
            final int userId = UserSingleton.getInstance().getId();

            System.out.println("de ingelezen taskid waarde is: " + taskId);
            System.out.println("de ingelezen availableHours waarde is: " + hours);


            // insert the data into database
            taskApplicationRepo.insertTaskapplication(userId, todaysDate, null, hours, "Docent", taskId);

            // get latest data from db en send to showtasks.html
            model.addAttribute("showTasks", taskRepository.getVacancies());
            return "showtasks";
        } else {
            return "showTasksError";
        }
    }

    @PostMapping(value = "/taskApplications/{taskId}/{fullName}/{availHours}")
    public String updateTaskApplications(HttpServletRequest request, ModelMap model) {

        //get the data from httpservletRequest and put in variable
        String tempId = request.getParameter("taskId");
        String fullName = request.getParameter("fullName");
        String tempHours = request.getParameter("availHours");
        String updateAction = request.getParameter("updateAppl");
        String deleteAction = request.getParameter("removeAppl");
        System.out.println("taskid retrieved from applicationBasket " + tempId);
        System.out.println("name  retrieved from applicationBasket " + fullName);
        System.out.println("nrHours retrieved from applicationBasket " + tempHours);

        //Convert variable to int
        if (tempHours.matches("^[0-9]+$")) {
            int taskId = Integer.parseInt(tempId);
            int hours = Integer.parseInt(tempHours);

            //haal de userId op vd loggedin user uit de Singleton
            final int userId = UserSingleton.getInstance().getId();

            //take action based on which button was clicked
            if (updateAction != null) {

                System.out.println("De user_id is ---> " + userId);
                taskApplicationRepo.updateHours(hours, userId, taskId);
                System.out.println("De UPDATE Methode is aangeroepen");

            } else if (deleteAction != null) {
                taskApplicationRepo.deleteApplication(taskId, userId);
                System.out.println("De DELETE Methode is aangeroepen");

            } else {
                System.out.println("ER GAAT IETS FOUT--> GEEN BUTTON IS GEKLIKT!!");
            }

            model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview(userId));
            return "applicationBasket";
        } else {
            return "applicationBasketError";
        }
    }


    @GetMapping(value = "/applicationBasket")
    public String fillApplicationBasket(Object object, Model model) {

        LocalDate begin = LocalDate.of(2018, 01, 01);
        LocalDate end = LocalDate.of(2018, 01, 16);

        //haal de userId op vd loggedin user uit de Singleton
        final int userId = UserSingleton.getInstance().getId();

        //model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview());
        model.addAttribute("applicationBasket", taskApplicationRepo.getApplicationOverview(userId));
        System.out.println("INHOUD VH OBJECT: ---> " + taskApplicationRepo.getApplicationOverview(userId).toString());
        return "applicationBasket";
    }
}