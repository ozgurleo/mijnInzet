package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject>findAll(){
        List<Subject>subjects=new ArrayList<>();
        subjects=subjectRepository.findAll();
        return subjects;

    }

    public void addSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public void deleteSubjectById(int id){
        subjectRepository.deleteById(id);
    }

}
