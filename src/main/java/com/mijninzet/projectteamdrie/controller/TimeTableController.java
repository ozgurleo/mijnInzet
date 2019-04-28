package com.mijninzet.projectteamdrie.controller;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeTableController {
    SessionFactory sessionFactory;

    @RequestMapping("timeTable")
    public String hello() {
        return "timeTable";
    }
}
