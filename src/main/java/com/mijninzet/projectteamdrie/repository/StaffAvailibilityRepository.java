package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.thymeleaf.context.Context;

import java.util.List;


@Repository
public interface StaffAvailibilityRepository extends CrudRepository<StaffAvailability,Integer> {

    List<StaffAvailability> findByUserId(int id);

    List<StaffAvailability> findAll();

    List<StaffAvailability> findAllByCohort(String cohort);

    List<StaffAvailability> findAllByUserIdAndCohort(int id, String cohort);

    StaffAvailability findById(int id);




}

