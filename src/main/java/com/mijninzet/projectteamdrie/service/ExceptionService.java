package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Exception;

import java.util.List;

public interface ExceptionService {
    public List<Exception> findAll();
    public Exception findById(int theId);
    public void save(Exception theException);
    public void deleteById(int theId);

}
