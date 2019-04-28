package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;

    @RequestMapping("timeTable")
    public String timeTable(Model model) {
        return "timeTable";
    }
}
