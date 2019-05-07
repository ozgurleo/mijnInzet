package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class TaskApplicationController {

    @Autowired
    TaskApplicationRepository taskApplicationRepo;

    @RequestMapping(value = "/showTasks")
    public void storeTaskApplication(TaskApplication taskAppl) {

    }

}
