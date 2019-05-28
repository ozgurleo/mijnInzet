package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Exception;
import com.mijninzet.projectteamdrie.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
    @Autowired
    private ExceptionService exceptionService;

    private List<Exception>exceptions;

    @RequestMapping("/list")
    public String listException(Model model){
        exceptions=exceptionService.getAll();

        model.addAttribute("exceptions",exceptions);
        return "exception/list-exceptions";
    }

    @GetMapping("/addException")
    public String addException(Model model){
        Exception theException = new Exception();
        model.addAttribute("exception", theException);
        return "exception/exception-form";

    }

    @PostMapping("/save")
    public String saveException(@ModelAttribute("exception") Exception theException){

        exceptionService.saveException(theException);
        return ("redirect:/exception/list");
    }


}
