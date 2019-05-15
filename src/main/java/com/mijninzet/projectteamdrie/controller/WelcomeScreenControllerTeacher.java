package com.mijninzet.projectteamdrie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeScreenControllerTeacher {

    @RequestMapping(value = "/helloTeacher")
    public String hello() {
        return "helloTeacher";
    }
}
