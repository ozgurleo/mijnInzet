package com.mijninzet.projectteamdrie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/cohort")

public class CohortController {


    @RequestMapping(value="/createCohort")
    public String createCohort() {
        return "createCohort";
    }


}
