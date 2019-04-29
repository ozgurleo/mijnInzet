package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;
    EntityManager entityManager;

    @RequestMapping("timeTable")
    public String timeTable(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "timeTable";
    }

}
