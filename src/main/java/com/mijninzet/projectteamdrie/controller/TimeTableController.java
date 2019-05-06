package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.dao.TimeTableDAO;
import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import com.mijninzet.projectteamdrie.repository.TimeTableRepository;
import com.mijninzet.projectteamdrie.service.TimeTableService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;


@Controller
public class TimeTableController {
    SessionFactory sessionFactory;
    TimeTableDAO timeTableDAO = new TimeTableDAO(sessionFactory);
    @Autowired
    TimeTableService timeTableService;
    @Autowired
    TimeTableRepository timeTableRepository;
    @Autowired
    StaffAvailibilityRepository staffAvailibilityRepository;


    @RequestMapping(value={"/showTimeTables"}, method = RequestMethod.GET)
    public String makeList(Model model) {
        model.addAttribute("timetables", timeTableRepository.findAll());
        model.addAttribute("datetime", new Date());
//        model.addAttribute("user_id", timeTableRepository.findAllById(user_id));
//        model.addAttribute("user_id", staffAvailibilityRepository.findStaffAvailabilityByUser(user_id));
        return "showTimeTables";

    }




//    @RequestMapping(value = "/showTimeTables")
//    public String showByYear(@RequestParam("year") int year) {
//       List<TimeTable> timetablesbyyear = timeTableService.findAllByYear(year);
//        return "ShowTimeTables";
//    }

    @RequestMapping(value="/showTimeTables/{year}")
    public String searchByYear(Model model, @PathVariable int year, @RequestParam(value="searchTerm", required=false)String searchTerm){
        List<TimeTable> getTimeTablesByYear = timeTableRepository.findAllByYear(year);
        model.addAllAttributes(getTimeTablesByYear);
        return "showTimeTables";
    }



}
