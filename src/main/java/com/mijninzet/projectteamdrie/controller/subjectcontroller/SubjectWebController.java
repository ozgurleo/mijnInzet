package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectWebController {
    @GetMapping("vak")
    public String subjectHomePage(){
        return "subject/subject-home";
    }

}
