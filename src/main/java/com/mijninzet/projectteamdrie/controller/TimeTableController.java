package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.TimeTableDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;
    TimeTableDAO timeTableDAO = new TimeTableDAO(sessionFactory);

    @RequestMapping("timeTable")
    public String timeTable(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "timeTable";
    }

    @RequestMapping(value={"/showTimeTables"}, method = RequestMethod.GET)
    public String makeList(Model model) {
       model.addAttribute("showTimeTables", timeTableDAO.findAllTimeTables());
        return "showTimeTables";
    }



}
