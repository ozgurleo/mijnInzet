package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import com.mijninzet.projectteamdrie.repository.TimeTableRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;
    EntityManager entityManager;
    TimeTableRepository timeTableRepository;

    @RequestMapping("timeTable")
    public String timeTable(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "timeTable";
    }

    @RequestMapping(value = "/showTableWithValues", method = RequestMethod.GET)
    public String showTableWithValues(Model model)
    {
        //list with timeTables
        ArrayList<TimeTable> timeTables=
                new ArrayList<TimeTable>();

        Iterable<TimeTable> iterable = timeTableRepository.findAll();
        for (TimeTable tt: timeTables
             ) {
            timeTables.add(tt);

        }
        model.addAttribute("list", timeTables);

        return "showTableWithValues";
    }



}
