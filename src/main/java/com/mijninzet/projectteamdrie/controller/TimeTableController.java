package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.TimeTableDAO;
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
import java.util.List;


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
