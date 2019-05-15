package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.ApplicationBasket;
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
import java.util.List;


@Repository
public interface TaskApplicationRepository extends JpaRepository<TaskApplication, Integer> {

    //retrieve data from multiple tables by means of inner joins
    @Query(value="SELECT T.task_id, T.task_name, T.estimated_hours, CONCAT(U.first_name, ' ', U.last_name) as 'fullname',TA.application_date, TA.available_hours  FROM task_application TA  INNER JOIN task T ON  TA.task_task_id=T.task_id INNER JOIN user U ON TA.user_id=U.user_id;", nativeQuery = true)
    List<ApplicationBasket> getApplicationOverview();


// store data into the task_application tabel
    @Modifying
    @Query(value = "INSERT INTO task_application (user_id, application_date,available_hours,role,unsubcribe_date,task_task_id)  VALUES (:userId,:applicationDate,:availableHours,:role,:unsubcribeDate,:taskID)",nativeQuery = true)
    @Transactional
    void insertTaskapplication(@Param("userId")Integer userId, @Param("applicationDate")LocalDate applicationDate ,
                               @Param("unsubcribeDate")LocalDate unsubcribeDate,
                               @Param("availableHours")Integer availableHours, @Param("role")String role, @Param("taskID")Integer taskID);
}
