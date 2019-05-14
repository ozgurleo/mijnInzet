package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;



@Repository
public interface TaskApplicationRepository extends JpaRepository<TaskApplication, Integer> {

    @Modifying
    @Query(value="INSERT INTO task_application (userId, applicationDate, unsubcribeDate, availableHours, role,taskID);", nativeQuery = true)
    @Transactional
    void insertTaskapplication(@Param("userId")Integer userId, @Param("applicationDate") LocalDate applicationDate , @Param("unsubcribeDate") LocalDate unsubcribeDate,
                              @Param("availableHours") Date availableHours, @Param("role") String role, @Param("taskID") String taskID);

}
