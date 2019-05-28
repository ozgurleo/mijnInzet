package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Exception;
import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.repository.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionService {
    @Autowired
    private ExceptionRepository exceptionRepository;

    public List<Exception> getAll(){
        ArrayList<Exception>exceptions = new ArrayList<>();
        exceptionRepository.findAll()
                .forEach(exceptions::add);
        return exceptions;
    }

    public void saveException(Exception e){
        exceptionRepository.save(e);
    }


}
