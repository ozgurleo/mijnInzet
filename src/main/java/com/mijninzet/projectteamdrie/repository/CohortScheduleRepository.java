package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface CohortScheduleRepository extends JpaRepository<CohortSchedule, Integer> {

    List<CohortSchedule> findByCohort(int cohortId);

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort;", nativeQuery = true)
    ArrayList<Object[]> getCohorts();

}
