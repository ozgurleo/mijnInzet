package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.TaskDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private SessionFactory sessionFactory;
    private TaskDAO taskDao = new TaskDAO(sessionFactory);

    //@RequestMapping(value = "/hello")
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    //@RequestMapping(value = "/showTasks")
    @RequestMapping("showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskDao.findAllTasks());

        // Dit is een testje
        List<Integer> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        model.addAttribute("test", test);
        // einde test

        return "showTasks";
    }

    public void applyToVacancy() {
        // hier komt de methode om op een taak te solliciteren
    }

}
