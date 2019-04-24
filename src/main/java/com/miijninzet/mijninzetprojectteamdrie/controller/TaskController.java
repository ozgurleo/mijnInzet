package com.miijninzet.mijninzetprojectteamdrie.controller;

import com.miijninzet.mijninzetprojectteamdrie.model.dao.TaskDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TaskController {
    private SessionFactory sessionFactory;
    private TaskDAO taskDao = new TaskDAO(sessionFactory);

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskDao.findAllTasks());
        System.out.println(model);
        return "showTasks";

    }

    public void applyToVacancy() {
        // hier komt de methode om op een taak te solliciteren
    }

}
