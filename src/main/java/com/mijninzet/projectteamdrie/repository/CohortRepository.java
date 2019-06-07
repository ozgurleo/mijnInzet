package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    List<Cohort> findAll();
    Date findByBeginDate(Date date);

    Cohort getByCohortId(int cohortId);




}
