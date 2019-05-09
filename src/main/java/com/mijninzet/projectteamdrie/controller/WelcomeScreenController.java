package com.mijninzet.projectteamdrie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeScreenController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
