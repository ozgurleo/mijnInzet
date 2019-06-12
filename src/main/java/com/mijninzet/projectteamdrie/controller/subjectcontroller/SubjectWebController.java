package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectWebController {
    @Autowired
    SubjectService subjectService;
    private List<Subject> subjectList;
    @GetMapping("/")
    public String subjectHomePage(){
        return "subject/subject-home";
    }

    @RequestMapping("/list")
    public String listSubject(Model model){
        subjectList=subjectService.findAll();
        model.addAttribute("subjects",subjectList);
        return "subject/list-subject";
    }
    @GetMapping("/addSubject")
    public String addSubject(Model model){
        Subject subject = new Subject();
        model.addAttribute("subject",subject);
        return "subject/subject-form";
    }
    @PostMapping("/save")
    public String saveSubject(@ModelAttribute("subject") Subject subject){
        subjectService.addSubject(subject);
        return ("redirect:/subject/list");
    }
    @GetMapping("/updateSubject")
    public String updateSubject(@RequestParam("subjectId") int subjectId,  Model model){
        Subject subject= subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        return "subject/subject-form";
    }

    @GetMapping("/deleteSubject")
    public String deleteSubject(@RequestParam("subjectId") int subjectId, Model model){
        subjectService.deleteSubjectById(subjectId);
        return ("redirect:/subject/list");
    }




}
