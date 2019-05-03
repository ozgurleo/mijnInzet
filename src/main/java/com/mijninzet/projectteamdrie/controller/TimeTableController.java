package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.TimeTableDAO;
import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import com.mijninzet.projectteamdrie.repository.TimeTableRepository;
import com.mijninzet.projectteamdrie.service.TimeTableService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;
    TimeTableDAO timeTableDAO = new TimeTableDAO(sessionFactory);
    TimeTableService timeTableService;


    @RequestMapping("timeTable")
    public String timeTable(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "timeTable";
    }

    @RequestMapping(value = "timeTable/add", method = RequestMethod.PUT)
        public void addTimeTable(Model model){
        }


    @RequestMapping(value={"/showTimeTables"}, method = RequestMethod.GET)
    public String makeList(Model model) {
        model.addAttribute("year", new TimeTableService().findAllByYear(2020));
        return "showTimeTables";
    }



}
