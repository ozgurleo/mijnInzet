package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Exception;
import com.mijninzet.projectteamdrie.service.ExceptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
    @Autowired
    private ExceptionServiceImp exceptionService;

    private List<Exception>exceptions;

    @RequestMapping("/list")
    public String listException(Model model){
        exceptions=exceptionService.findAll();

        model.addAttribute("exceptions",exceptions);
        return "exception/list-exceptions";
    }

    @GetMapping("/addException")
    public String addException(Model model){
        Exception theException = new Exception();
        model.addAttribute("exception", theException);
        return "exception/exception-form";

    }

    @GetMapping("/updateException")
    public String updateException(@RequestParam("exceptionId") int theId, Model model){
        Exception theException=exceptionService.findById(theId);
        model.addAttribute("exception",theException);
        return "exception/exception-form";
    }

    @PostMapping("/save")
    public String saveException(@ModelAttribute("exception") Exception theException){

        exceptionService.save(theException);


        return ("redirect:/exception/list");
    }

    @GetMapping("/deleteException")
    public String deleteException(@RequestParam ("exceptionId") int theId){
        exceptionService.deleteById(theId);
        return ("redirect:/exception/list");

    }


}
