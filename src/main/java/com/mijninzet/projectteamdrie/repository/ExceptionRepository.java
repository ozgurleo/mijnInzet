package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Exception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionRepository extends CrudRepository<Exception,Integer>{
    List<Exception> findAll();
    List<Exception> findByUser_Id(int id);
}
