package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CohortScheduleRepository extends JpaRepository<CohortSchedule, Integer> {

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort;", nativeQuery = true)
    ArrayList<Object[]> getCohorts();

}
